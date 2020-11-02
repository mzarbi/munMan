package com.nogroup.municipality.manager.ccmp.application;

import com.nogroup.municipality.manager.VUI;
import com.vaadin.server.Resource;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

public class MenuRtl extends AbstractMenu{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;

	public MenuRtl(VUI ui) {
		super(ui);
	}

	@Override
	public MenuItem addItem(String caption, Command cmd, MenuItemType tp, Resource icon, boolean isCheckable) {
		return null;
	}

}
