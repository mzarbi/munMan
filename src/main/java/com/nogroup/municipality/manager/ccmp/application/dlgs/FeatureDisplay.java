package com.nogroup.municipality.manager.ccmp.application.dlgs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.vol3.feature.OLFeature;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.ccmp.wndws.CreateWindow;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

public class FeatureDisplay extends CreateWindow{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private Grid<FeatureBean> grid;
	private OLFeature feature;
	private List<FeatureBean> properties;

	public FeatureDisplay(VUI ui, OLFeature olFeature) {
		super(ui);
		this.feature = olFeature ;
		getSaveBtn().setVisible(false);
		
		for(String tmp : olFeature.getProperties().keySet()) {
			properties.add(new FeatureBean(tmp,"" + olFeature.get(tmp))) ;
		}
		
		if(olFeature.getProperties().get("type").equals("DWELLING UNIT")) {
			addDropDown("Downloads", new String[] {
				"Ownership Certificate",
				"Application Form",
				"Revenu declaration",
				"Topographic plan"
			}, new Command() {
				
				@Override
				public void menuSelected(MenuItem selectedItem) {
					
				}
			});
		}
	}

	@Override
	public void saveButtonClicked() {
		
	}

	@Override
	public String caption() {
		return "Feature";
	}

	@Override
	public ComponentContainer content() {
		properties = new ArrayList<>() ;
		
		VerticalLayout vl = new VerticalLayout() ;
		vl.setSizeFull();
		
		grid = new Grid<>();
		grid.setItems(properties);
		grid.addColumn(FeatureBean::getField).setCaption("Field");
		grid.addColumn(FeatureBean::getValue).setCaption("Value");
		grid.setSelectionMode(SelectionMode.MULTI) ;
		grid.setSizeFull();
		vl.addComponent(grid);
		
		return vl;
	}

	@Override
	public void cancelButtonCallBack() {
		FeatureDisplay.this.close();
	}
	
	
	protected class FeatureBean implements Serializable{
		/**
		 * @author medzied
		 */
		private static final long serialVersionUID = 1L;
		
		private String field ;
		private String value ;
		
		
		public FeatureBean(String field, String value) {
			super();
			this.field = field;
			this.value = value;
		}
		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

}
