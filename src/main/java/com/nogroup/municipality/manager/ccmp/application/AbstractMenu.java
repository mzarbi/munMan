package com.nogroup.municipality.manager.ccmp.application;

import com.nogroup.municipality.manager.VUI;
import com.vaadin.server.Resource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

public abstract class AbstractMenu extends HorizontalLayout{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private VUI ui;
	
	public AbstractMenu(VUI ui) {
		this.ui = ui ;
	}
	
	public abstract MenuItem addItem(String caption, MenuBar.Command cmd,MenuItemType tp,Resource icon, boolean isCheckable) ;
	
	public MenuItem addItem(String caption, MenuBar.Command cmd,MenuItemType tp) {
		return addItem(caption, cmd,tp,null,false);
	}
	public MenuItem addItem(String caption, MenuBar.Command cmd,MenuItemType tp,Resource icon) {
		return addItem(caption, cmd,tp,icon,false);
	}
	public MenuItem addItem(String caption, MenuBar.Command cmd,MenuItemType tp,boolean isCheckable) {
		return addItem(caption, cmd,tp,null,isCheckable);
	}

	public VUI getUi() {
		return ui;
	}
	
}
