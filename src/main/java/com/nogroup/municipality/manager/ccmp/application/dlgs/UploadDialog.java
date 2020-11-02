package com.nogroup.municipality.manager.ccmp.application.dlgs;

import java.util.UUID;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.ccmp.flds.UploadField;
import com.nogroup.municipality.manager.ccmp.wndws.CreateWindow;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UploadDialog extends CreateWindow{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private UploadDialogCallbacks callback;
	private UploadField uploadField;
	private String format;
	private TextField tf;

	public UploadDialog(VUI ui, String format,UploadDialogCallbacks callback) {
		super(ui);
		this.format = format ;
		this.callback = callback ;
		setHeight("50%");
	}

	@Override
	public void saveButtonClicked() {
		if(uploadField.getValue().getContent() == null) {
			ui.showNotification("Please upload a file", com.vaadin.ui.Notification.TYPE_ERROR_MESSAGE);
			return ;
		}
		if(!uploadField.getValue().getName().toLowerCase().endsWith(format.toLowerCase())) {
			ui.showNotification("Please upload a file with the proper format", com.vaadin.ui.Notification.TYPE_ERROR_MESSAGE);
			return ;
		}
		if(tf.getValue().equals("")) {
			this.callback.onOk(uploadField.getValue(),"Layer_" + UUID.randomUUID().toString());
			UploadDialog.this.close();
			return ;
		}
		this.callback.onOk(uploadField.getValue(),tf.getValue());
		UploadDialog.this.close();
	}

	@Override
	public String caption() {
		return "Upload File";
	}

	@Override
	public ComponentContainer content() {
		
		VerticalLayout vl = new VerticalLayout() ;
		tf = new TextField();
		tf.setPlaceholder("Display Name");
		tf.setWidth("100%");
		vl.addComponent(tf);
		vl.addComponent(uploadField = new UploadField(""));
		vl.setSizeFull();
		return vl ;
	}

	@Override
	public void cancelButtonCallBack() {
		UploadDialog.this.close();
		this.callback.onCancel();
	}

}
