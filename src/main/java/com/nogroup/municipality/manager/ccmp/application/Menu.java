package com.nogroup.municipality.manager.ccmp.application;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.business.ResourceBundle;
import com.nogroup.municipality.manager.ccmp.dlgs.ConfirmDialog;
import com.nogroup.municipality.manager.modules.login.LoginView;
import com.nogroup.municipality.manager.modules.moduleChooser.ModuleChooserView;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

public class Menu extends AbstractMenu{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private MenuBar menu;
	private MenuBar toolbox;

	public Menu(VUI ui) {
		super(ui);
		init() ;
		style();
	}

	protected void init() {
		menu = new MenuBar() ;
		menu.setSizeFull();
		menu.addStyleNames(ValoTheme.MENUBAR_SMALL,ValoTheme.MENUBAR_BORDERLESS);
		addComponent(menu);
		//setExpandRatio(menu, (float) 0.9);
		
		toolbox = new MenuBar() ;
		toolbox.setHeight("100%");
		toolbox.addStyleNames(ValoTheme.MENUBAR_SMALL,ValoTheme.MENUBAR_BORDERLESS);
		addComponent(toolbox);
		setComponentAlignment(toolbox, Alignment.MIDDLE_RIGHT);
	}
	
	protected void style() {
		setWidth("100%");
		setSpacing(false);	
	}
	@Override
	public MenuItem addItem(String caption, Command cmd, MenuItemType tp, Resource icon, boolean isCheckable) {
		if(tp.equals(MenuItemType.MENU)) {
			MenuItem item = menu.addItem(caption, cmd) ;
			item.setCheckable(isCheckable);
			if(icon != null) {
				item.setIcon(icon);
			}
			return item ;
		}else {
			MenuItem item = toolbox.addItem(caption, cmd) ;
			item.setCheckable(isCheckable);
			if(icon != null) {
				item.setIcon(icon);
			}
			return item ;
		}
	}

	public void initUserPreferences() {
		MenuItem usrPrefs = toolbox.addItem("") ;
		usrPrefs.setIcon(ResourceBundle.getResource("user.png",24));
		
		usrPrefs.addItem("User Profile");
		usrPrefs.addItem("Change Password");
		usrPrefs.addSeparator();
		usrPrefs.addItem("Back", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				getUi().addWindow(new ConfirmDialog(getUi(),"Are you sure ?","By Logging out all monitoring sessions will be closed.") {
					
					@Override
					public void onOk() {
						getUi().setContent(new ModuleChooserView(getUi()));
					}
					
					@Override
					public void onCancel() {
						
					}
				});
			}
		});
		usrPrefs.addItem("Logout", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				getUi().addWindow(new ConfirmDialog(getUi(),"Are you sure ?","By Logging out all monitoring sessions will be closed.") {
					
					@Override
					public void onOk() {
						getUi().getSession().setAttribute("username", null);
						getUi().setContent(new LoginView(getUi()));
					}
					
					@Override
					public void onCancel() {
						
					}
				});
			}
		});
		
	}
}
