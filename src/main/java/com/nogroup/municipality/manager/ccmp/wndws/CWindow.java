package com.nogroup.municipality.manager.ccmp.wndws;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class CWindow extends Window {

	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	
	VerticalLayout vl = new VerticalLayout();
	VerticalLayout content = new VerticalLayout() ;
	VerticalLayout footer = new VerticalLayout ();
	private HorizontalLayout hznl;
	private Panel pane;
	
	
	public void init() {

		center();
		vl.setSizeFull();
		vl.setDebugId("root");
		setContent(vl);
		
		content.setHeight("100%");
		content.addStyleName("small-margins");
		content.setWidth("100%");
		content.setDebugId("content");
		
		pane = new Panel() ;
		pane.setSizeFull();
		content.addComponent(pane);
		
		vl.addComponent(content);
		vl.addStyleName("small-margins");
		vl.setExpandRatio(content, 1);
		
		
	}
	
	public void addContent(ComponentContainer cmp){
		pane.setContent(cmp);
	}
	
	protected void addContent(Component cmp) {
		pane.setContent(cmp);
		
	}
	public void addFooter(){
		footer.setHeight("35px");
		footer.setWidth("100%");
		footer.setDebugId("footer");
		footer.setMargin(new MarginInfo(false,true,false,false));
		footer.addStyleName("small-margins");
		vl.addComponent(footer);
		
		hznl = new HorizontalLayout() ;
		hznl.setSpacing(true);
		//hznl.setMargin(false,true,false,false);
		footer.addComponent(hznl);
		footer.setComponentAlignment(hznl, Alignment.MIDDLE_RIGHT);
		footer.addStyleName("backColorGrey");
	}
	
	public NativeButton addNativeButton(String caption,Button.ClickListener listener){
		NativeButton btn = new NativeButton(caption,listener) ;
		hznl.addComponent(btn);
		hznl.setComponentAlignment(btn, Alignment.MIDDLE_LEFT);
		return btn ;
	}
	
	public Button addButton(String caption,Button.ClickListener listener){
		Button btn = new Button(caption,listener) ;
		btn.addStyleName(ValoTheme.BUTTON_TINY);
		hznl.addComponent(btn);
		hznl.setComponentAlignment(btn, Alignment.MIDDLE_LEFT);
		return btn ;
	}

	public NativeButton addButton(String caption) {
		NativeButton btn = new NativeButton(caption) ;
		hznl.addComponent(btn);
		hznl.setComponentAlignment(btn, Alignment.MIDDLE_LEFT);
		return btn ;
	}
	
	public NativeSelect addSelector(String caption,String[] items) {
		NativeSelect select = new NativeSelect() ;
		select.setItems(items);
		hznl.addComponent(select);
		hznl.setComponentAlignment(select, Alignment.MIDDLE_LEFT);
		return select ;
	}
	
	public MenuBar addDropDown(String caption,String[] items,Command cmd) {
		MenuBar select = new MenuBar();
		select.addStyleName(ValoTheme.MENUBAR_SMALL);
		MenuItem mn = select.addItem(caption);
		for(String tmp : items) {
			mn.addItem(tmp,cmd);
		}
		hznl.addComponent(select);
		hznl.setComponentAlignment(select, Alignment.MIDDLE_LEFT);
		return select ;
	}

}
