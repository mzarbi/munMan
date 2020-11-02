package com.nogroup.municipality.manager.modules.login;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.data.daos.UserD;
import com.nogroup.municipality.manager.data.entities.User;
import com.nogroup.municipality.manager.modules.moduleChooser.ModuleChooserView;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LoginView extends VerticalLayout implements Button.ClickListener {
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private VUI ui ;
	private TextField usernameF;
	private TextField pwdF;
	private CheckBox chbx;
	private Button btn;
	private Button frgtnBtn;
	
	public LoginView(VUI ui) {
		this.ui = ui ;
		setSizeFull();
		setMargin(false);
		addStyleName("loginBackground");
		
		
		CustomLayout cstm = new CustomLayout("login") ;
		cstm.setSizeFull();
		cstm.addComponent(usernameF = new TextField(), "username");
		usernameF.addStyleName("loginTextfield");
		usernameF.setValue("medzied.arbi@gmail.com");
		usernameF.setWidth("100%");
		
		cstm.addComponent(pwdF = new PasswordField(), "password");
		pwdF.setValue("0101");
		pwdF.setWidth("100%");
		
		cstm.addComponent(chbx = new CheckBox(), "checkbx");
		chbx.setWidth("100%");
		
		cstm.addComponent(btn = new Button("Login",this), "confirmBtn");
		btn.setWidth("100%");
				
		cstm.addComponent(frgtnBtn = new Button("Forgot Password?", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				ui.addWindow(new ForgottenPasswordView(ui,usernameF.getValue()));
			}
		}), "frgtnBtn");
		frgtnBtn.addStyleName(ValoTheme.BUTTON_LINK);
		//chbx.setWidth("100%");
		
		addComponent(cstm);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void buttonClick(ClickEvent event) {
		String usrnm = usernameF.getValue() ;
		String pwd = pwdF.getValue() ;
		
		User us = new UserD().authenticate(usrnm,pwd) ;
		if(us == null) {
			ui.showNotification("Wrong Credentials",Notification.TYPE_ERROR_MESSAGE);
		}else {
			
			ui.getSession().setAttribute("username", usrnm);
			ui.setContent(new ModuleChooserView(ui));
			ui.showNotification("Welcome " + us.getName().getEnName() ,Notification.TYPE_TRAY_NOTIFICATION);
		}
	}

}
