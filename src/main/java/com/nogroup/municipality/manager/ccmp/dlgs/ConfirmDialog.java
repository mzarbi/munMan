package com.nogroup.municipality.manager.ccmp.dlgs;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.ccmp.wndws.CreateWindow;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class ConfirmDialog extends CreateWindow{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private String caption;
	private String msg;

	public ConfirmDialog(VUI ui,String caption,String msg) {
		super(ui,false);
		this.caption = caption ;
		this.msg = msg ;
		
		initCreateWindow();
		setResizable(false);
		setWidth("30%");
		setHeight("40%");
		setModal(true);
		
		getSaveBtn().setCaption("Ok");
		getCancelBtn().setCaption("Cancel");
	}

	@Override
	public String caption() {
		return this.caption;
	}

	@Override
	public ComponentContainer content() {
		VerticalLayout vl = new VerticalLayout() ;
		vl.setSizeFull();
		Label lbl = new Label(msg,ContentMode.TEXT);
		vl.addComponent(lbl);
		lbl.setSizeFull();
		return vl;
	}

	@Override
	public void saveButtonClicked() {
		onOk();
		ConfirmDialog.this.close();
	}

	@Override
	public void cancelButtonCallBack() {
		onCancel();
		ConfirmDialog.this.close();
	}
	
	public abstract void onOk();
	public abstract void onCancel();
}
