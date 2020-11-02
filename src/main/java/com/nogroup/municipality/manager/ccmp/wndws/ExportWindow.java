package com.nogroup.municipality.manager.ccmp.wndws;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public abstract class ExportWindow extends CWindow {

	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CForm form = new CForm() ;
	public Button exportBtn;
	public Button closeBtn;

	public ExportWindow(){
		
	}
	
	public void initExportWindow(){
		setWidth("60%");
		setHeight("50%");
		init();
		addFooter();
		
		exportBtn = addButton("Export",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				
			}
		}) ;
		
		closeBtn = addButton("Close",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				ExportWindow.this.close();

			}
		}) ;
		
		setCaption(caption());
		addContent(form);
	}
	
	public abstract String caption() ;
	
	public class CForm extends VerticalLayout{

		public CForm(){
			
		}
	}
}
