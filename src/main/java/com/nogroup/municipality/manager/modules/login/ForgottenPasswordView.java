package com.nogroup.municipality.manager.modules.login;

import java.util.UUID;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.business.PasswordUtils;
import com.nogroup.municipality.manager.business.SMSUtils;
import com.nogroup.municipality.manager.ccmp.wndws.CreateWindow;
import com.nogroup.municipality.manager.data.daos.UserD;
import com.nogroup.municipality.manager.data.entities.User;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ForgottenPasswordView extends CreateWindow{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private TextField tf2;
	private Button btn2;
	protected String ss;
	private PasswordField tf21;
	private PasswordField tf22;
	private String email;
	private TextField tf;

	public ForgottenPasswordView(VUI ui,String email) {
		super(ui);
		this.email = email ;
		
		tf.setValue(email);
		setHeight("80%");
	}

	@Override
	public void saveButtonClicked() {
		if(tf21.getValue().equals(tf22.getValue())) {
			User us = new UserD().getUserByEmail(email) ;
			us.setPassword(new String(PasswordUtils.hash(tf21.getValue().toCharArray(), us.getSalt().getBytes())));
			
			new UserD().hard_update(us);
			ui.showNotification("Password updated with success",Notification.TYPE_ERROR_MESSAGE);
			ForgottenPasswordView.this.close();
		}else {
			ui.showNotification("Wrong combination",Notification.TYPE_ERROR_MESSAGE);
		}
		
	}

	@Override
	public String caption() {
		return "Forgotten Password";
	}

	@Override
	public ComponentContainer content() {
		VerticalLayout vl = new VerticalLayout() ;
		vl.setWidth("100%");
		
		tf = new TextField() ;
		tf.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tf.setPlaceholder("Please enter your email address");
		tf.setValue("");
		tf.setEnabled(false);
		tf.setWidth("100%");
		vl.addComponent(tf);
		
		Button btn = new Button("Send Verification SMS", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				User us = new UserD().getUserByEmail(email) ;
				if(us == null) {
					ui.showNotification("Wrong Email",Notification.TYPE_ERROR_MESSAGE);
				}else {
					ss = UUID.randomUUID().toString().substring(0, 5) ;
					SMSUtils.sendSMS( ss, "+216" + us.getPhone());
					event.getButton().setCaption("Resend Verification SMS");
					tf2.setVisible(true);
					btn2.setVisible(true);
				}
			}
		});
		btn.addStyleName(ValoTheme.BUTTON_TINY);
		vl.addComponent(btn);
		
		tf2 = new TextField() ;
		tf2.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tf2.setPlaceholder("Please Enter token");
		tf2.setWidth("100%");
		tf2.setVisible(false);
		vl.addComponent(tf2);
		
		btn2 = new Button("Validate", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(ss.equals(tf2.getValue())) {
					ui.showNotification("Valid Verification code",Notification.TYPE_TRAY_NOTIFICATION);
					tf21.setVisible(true);
					tf22.setVisible(true);
				}else {
					ui.showNotification("Wrong verification code");
				}
			}
		});
		vl.addComponent(btn2);
		btn2.setVisible(false);
		btn2.addStyleName(ValoTheme.BUTTON_TINY);
		
		tf21 = new PasswordField() ;
		tf21.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tf21.setPlaceholder("Enter your new password");
		tf21.setWidth("100%");
		tf21.setVisible(false);
		vl.addComponent(tf21);
		
		tf22 = new PasswordField() ;
		tf22.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tf22.setPlaceholder("Re-enter your new password");
		tf22.setWidth("100%");
		tf22.setVisible(false);
		vl.addComponent(tf22);
		
		
		return vl;
	}

	@Override
	public void cancelButtonCallBack() {
		ForgottenPasswordView.this.close();
	}

}
