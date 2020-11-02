package com.nogroup.municipality.manager.ccmp.wndws;

import java.util.Observable;
import java.util.Observer;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public abstract class ManagementWindow extends CWindow implements Observer{

	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private Button editBtn;
	private Button saveBtn;
	
	public ManagementWindow(){
	}
	
	public void initManagementWindow(){
		setWidth("50%");
		setHeight("50%");
		init();
		addFooter();
		editBtn = addButton("Edit",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				editButtonCallBack() ;
				update(null, true);
			}
		}) ;
		saveBtn = addButton("Save",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				saveButtonCallBack() ;
				update(null, false);
			}
		}) ;
		saveBtn.setEnabled(false);
		addButton("Delete",new ClickListener() {
	
			@Override
			public void buttonClick(ClickEvent event) {
				deleteButtonCallBack() ;
			}
		}) ;
		addButton("Close",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				cancelButtonCallBack() ;
			}
		}) ;
	}
	
	public abstract void saveButtonCallBack() ;
	public abstract void editButtonCallBack() ;
	public abstract void deleteButtonCallBack() ;
	
	public void cancelButtonCallBack(){
		this.close();
	}
	
	@Override
    public void update(Observable o, Object v) {
        this.saveBtn.setEnabled((boolean) v);
        this.editBtn.setEnabled(!(boolean)v);
    }

}
