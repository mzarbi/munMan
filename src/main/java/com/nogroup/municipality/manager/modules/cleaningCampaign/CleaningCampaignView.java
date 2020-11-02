package com.nogroup.municipality.manager.modules.cleaningCampaign;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.business.ResourceBundle;
import com.nogroup.municipality.manager.ccmp.application.Application;
import com.nogroup.municipality.manager.ccmp.application.MapViewer;
import com.nogroup.municipality.manager.ccmp.application.Menu;
import com.nogroup.municipality.manager.ccmp.application.MenuItemType;
import com.nogroup.municipality.manager.ccmp.application.dlgs.LayerManager;
import com.nogroup.municipality.manager.modules.cleaningCampaign.dlgs.NewCleaningCampaign;
import com.vaadin.contextmenu.ContextMenu;
import com.vaadin.contextmenu.client.ContextMenuItemState;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class CleaningCampaignView  extends Application{

	public CleaningCampaignView(VUI ui) {
		super(ui);
	}

	@Override
	public void before_gui() {
		
	}

	@Override
	public void init_menu(Menu menu) {
		MenuItem mn = menu.addItem("Campaign", null, MenuItemType.MENU);
		mn.addItem("New Campaign",new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				getUi().addWindow(new NewCleaningCampaign(getUi(), getMap(), true));
			}
		});
		mn.addItem("Manage");
		mn.addSeparator();
		mn.addItem("Calendar");
		
		MenuItem mn1 = menu.addItem("Collaborators", null,  MenuItemType.MENU);
		MenuItem mn11 = mn1.addItem("Agent");
		MenuItem mn12 = mn1.addItem("Organization");
		MenuItem mn13 = mn1.addItem("Sponsor");

		mn11.addItem("Create");
		mn11.addItem("Manage");
		
		mn12.addItem("Create");
		mn12.addItem("Manage");
		
		mn13.addItem("Create");
		mn13.addItem("Manage");

		
		MenuItem tgl = menu.addItem("", null, MenuItemType.TOOLBOX,ResourceBundle.getResource("navigation.png",24),true) ;
		tgl = menu.addItem("", null, MenuItemType.TOOLBOX,ResourceBundle.getResource("refresh.png",24),true) ;
		tgl = menu.addItem("", null, MenuItemType.TOOLBOX,ResourceBundle.getResource("trajectory.png",24),true) ;
		tgl = menu.addItem("", null, MenuItemType.TOOLBOX,ResourceBundle.getResource("marker.png",24),true) ;
		tgl = menu.addItem("", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				getMap().addSelectionInteraction(selectedItem.isChecked()) ;
			}
		}, MenuItemType.TOOLBOX,ResourceBundle.getResource("tap.png",24),true) ;
		tgl = menu.addItem("", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				getUi().addWindow(new LayerManager(getUi(),getMap(), new CloseListener() {

					@Override
					public void windowClose(CloseEvent e) {
						selectedItem.setChecked(false);
					}
					
				}));
			}
		}, MenuItemType.TOOLBOX,ResourceBundle.getResource("layers.png",24),false) ;
	}

	@Override
	public void init_map(MapViewer menu) {
		
	}

}
