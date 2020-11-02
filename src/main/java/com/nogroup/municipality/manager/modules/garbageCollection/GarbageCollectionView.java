package com.nogroup.municipality.manager.modules.garbageCollection;

import org.vaadin.addon.vol3.layer.OLVectorLayer;
import org.vaadin.addon.vol3.source.OLVectorSource;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.business.ResourceBundle;
import com.nogroup.municipality.manager.ccmp.application.Application;
import com.nogroup.municipality.manager.ccmp.application.MapViewer;
import com.nogroup.municipality.manager.ccmp.application.Menu;
import com.nogroup.municipality.manager.ccmp.application.MenuItemType;
import com.nogroup.municipality.manager.ccmp.application.dlgs.LayerManager;
import com.nogroup.municipality.manager.modules.garbageCollection.dlgs.NewGarbageCollectingUnit;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class GarbageCollectionView extends Application{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private OLVectorSource unitPositionLayerSource;
	private OLVectorLayer unitPositionLayer;
	private OLVectorSource trajectoriesLayerSource;
	private OLVectorLayer trajectoriesLayer;
	private OLVectorSource collectionPointsLayerSource;
	private OLVectorLayer collectionPointsLayer;

	public GarbageCollectionView(VUI ui) {
		super(ui);		
	}

	@Override
	public void before_gui() {
		
	}

	@Override
	public void init_menu(Menu menu) {
		MenuItem fm = menu.addItem("Fleet Management", null, MenuItemType.MENU) ;
		MenuItem nw = fm.addItem("New ..") ;
		nw.addItem("New Unit", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				getUi().addWindow(new NewGarbageCollectingUnit(getUi(),getMap()));
			}
		}) ;
		nw.addItem("New Trajectory") ;
		nw.addItem("New Collection Point") ;
		nw.addItem("New Recycling Point") ;
		MenuItem edt = fm.addItem("Edit") ;
		edt.addItem("Manage Fleet");
		edt.addItem("Manage Trajectory");
		
		MenuItem cp = menu.addItem("Statistics", null, MenuItemType.MENU) ;
		MenuItem rd = menu.addItem("Settings", null, MenuItemType.MENU) ;
		
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
		
		unitPositionLayerSource = new OLVectorSource() ;
		unitPositionLayer = new OLVectorLayer(unitPositionLayerSource) ;
		unitPositionLayer.setTitle("UNITS");
		unitPositionLayer.setLayerVisible(true);
		getMap().addLayer(unitPositionLayer);
		
		trajectoriesLayerSource = new OLVectorSource() ;
		trajectoriesLayer = new OLVectorLayer(trajectoriesLayerSource) ;
		trajectoriesLayer.setTitle("TRAJECTORIES");
		trajectoriesLayer.setLayerVisible(true);
		getMap().addLayer(trajectoriesLayer);
		
		collectionPointsLayerSource = new OLVectorSource() ;
		collectionPointsLayer = new OLVectorLayer(collectionPointsLayerSource) ;
		collectionPointsLayer.setTitle("COLLECTION POINTS");
		collectionPointsLayer.setLayerVisible(true);
		getMap().addLayer(collectionPointsLayer);
	
	}

}
