package com.nogroup.municipality.manager.ccmp.application;

import java.util.HashMap;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.business.ResourceBundle;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.MenuBar.MenuItem;

public abstract class Application extends VerticalLayout{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private VUI ui;
	protected Menu header ;
	//private MapV map ;
	private HashMap<Window,Button> locater = new HashMap<>() ;
	private MapViewer map;
	
	public Application(VUI ui) {
		this.ui = ui ;
		before_gui();
		init_gui() ;
		defaultStyle() ;
	}
	
	public abstract void before_gui() ;
	
	protected void init_gui() {
		header = new Menu(ui) ;
		addComponent(header);
		
		
		map = new MapViewer(ui) ;
		addComponent(map);
		
		init_menu(header);
		init_map(map) ;
		header.initUserPreferences();
		/*
		footer = new Footer(ui);
		addComponent(footer);
		*/
	}
	
	protected void defaultStyle() {
		setMargin(false);
		setSpacing(false);
		setSizeFull();
		
		setExpandRatio(map, 1);
	}
	
	public abstract void init_menu(Menu menu);
	public abstract void init_map(MapViewer menu);
	/*
	public void addWindow(Window win) {
		Button btn = footer.addButton(win.getCaption(), new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				win.setVisible(!win.isVisible());
			}
		});
		locater.put(win,btn) ;
		ui.addWindow(win);
	}
	
	public void removeButton(Window win) {
		Button btn = locater.get(win) ;
		footer.removeButton(btn);
	}

	public Window getWindow(String string) {
		for(Window tmp : locater.keySet()) {
			if(tmp.getCaption().equals(string)) {
				return tmp ;
			}
		}
		return null ;
	}*/

	public Menu getHeader() {
		return header;
	}
	public MapViewer getMap() {
		return map;
	}
	public VUI getUi() {
		return ui ;
	}
}
