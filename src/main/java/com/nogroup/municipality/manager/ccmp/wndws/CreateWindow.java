package com.nogroup.municipality.manager.ccmp.wndws;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.ccmp.application.MapViewer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;

public abstract class CreateWindow extends CWindow{

	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	public Button saveBtn;
	public Button cancelBtn;
	protected VUI ui;
	private MapViewer map;
	
	public CreateWindow(VUI ui) {
		this.ui = ui ;
		initCreateWindow();
	}
	
	public CreateWindow(VUI ui,boolean initialize) {
		this.ui = ui ;
		if(initialize) {
			initCreateWindow();
		}
		
	}
	
	public CreateWindow(VUI ui,MapViewer map,boolean initialize) {
		this.ui = ui ;
		this.map = map ;
		if(initialize) {
			initCreateWindow();
		}
		
	}
	public CreateWindow(VUI ui, MapViewer map) {
		this.ui = ui ;
		this.map = map ;
		initCreateWindow();
	}

	public void initCreateWindow(){
		setWidth("50%");
		setHeight("60%");
		init();
		addFooter();
		saveBtn = addButton("Save",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				saveButtonCallBack() ;
			}
		}) ;
		cancelBtn = addButton("Close",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				cancelButtonCallBack() ;
			}
		}) ;
		
		setCaption(caption());
		addContent(content());
		setClosable(false);
	}
	public void saveButtonCallBack(){
		saveButtonClicked() ;
	}
	
	public VUI getUi() {
		return ui;
	}
	
	public abstract void saveButtonClicked() ;
	public abstract String caption() ;
	public abstract ComponentContainer content() ;
	public abstract void cancelButtonCallBack() ;
	public Button getSaveBtn() {
		return saveBtn;
	}
	public void setSaveBtn(Button saveBtn) {
		this.saveBtn = saveBtn;
	}
	public Button getCancelBtn() {
		return cancelBtn;
	}
	public void setCancelBtn(Button cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	public MapViewer getMap() {
		return map;
	}
}
