package com.nogroup.municipality.manager.ccmp.application.dlgs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vaadin.addon.vol3.feature.OLFeature;
import org.vaadin.addon.vol3.source.OLVectorSource;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.ccmp.wndws.CreateWindow;
import com.vaadin.data.ValueProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;

public class LayerEntry extends CreateWindow{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;

	private Grid<RowBean> grid;
	private List<RowBean> properties;
	private OLVectorSource source ;
	
	public LayerEntry(VUI ui,OLVectorSource src) {
		super(ui,false);
		this.source= src ;
		initCreateWindow();
	}

	@Override
	public void saveButtonClicked() {
		LayerEntry.this.close();
	}

	@Override
	public String caption() {
		return "Layer Entry";
	}

	@Override
	public ComponentContainer content() {
		properties = new ArrayList<>() ;
		
		VerticalLayout vl = new VerticalLayout() ;
		vl.setSizeFull();
		
		int i = 0 ;
		for(OLFeature tmp : source.getFeatures()) {
			properties.add(new RowBean(i, tmp.getProperties()));
			i++ ;
		}
		grid = new Grid<>();
		grid.setItems(properties);
		
		grid.addColumn(RowBean::getId).setCaption("id");
		for(String tmp : source.getFeatures().get(0).getProperties().keySet()) {
			grid.addColumn(new ValueProvider<RowBean, String>() {

				@Override
				public String apply(RowBean source) {
					return "" + source.getValue().get(tmp);
				}
				
			}).setCaption(tmp);
		}
		
		grid.setSelectionMode(SelectionMode.MULTI) ;
		grid.setSizeFull();
		vl.addComponent(grid);
		
		return vl;
	}

	@Override
	public void cancelButtonCallBack() {
		LayerEntry.this.close();
	}
	
	protected class RowBean implements Serializable{
		/**
		 * @author medzied
		 */
		private static final long serialVersionUID = 1L;
		
		private int id ;
		private Map<String,String> value ;
		
		
		public RowBean(int field, Map<String, String> map) {
			super();
			this.id = field;
			this.value = map;
		}
		public int getId() {
			return id;
		}
		public void setId(int field) {
			this.id = field;
		}
		public Map<String,String> getValue() {
			return value;
		}
		public void setValue(Map<String,String> value) {
			this.value = value;
		}
	}

}
