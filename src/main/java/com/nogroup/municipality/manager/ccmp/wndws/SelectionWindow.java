package com.nogroup.municipality.manager.ccmp.wndws;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;

public abstract class SelectionWindow extends CWindow {

	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	
	public SelectionWindow(){
		
	}
	
	public void initSelectionWindow(){
		setWidth("30%");
		setHeight("50%");
		init();
		addFooter();
		addButton("Close",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				SelectionWindow.this.close();
			}
		}) ;
		
		setCaption(caption());
		addContent(content());
	}
	public abstract String caption() ;
	public abstract ComponentContainer content() ;
}
