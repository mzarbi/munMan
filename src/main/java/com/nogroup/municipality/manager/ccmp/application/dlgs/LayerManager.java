package com.nogroup.municipality.manager.ccmp.application.dlgs;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.geojson.FeatureCollection;
import org.vaadin.addon.vol3.layer.OLLayer;
import org.vaadin.addon.vol3.layer.OLVectorLayer;
import org.vaadin.addon.vol3.source.OLVectorSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.ccmp.application.MapViewer;
import com.nogroup.municipality.manager.ccmp.wndws.CreateWindow;
import com.nogroup.municipality.manager.data.embedded.VFile;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

public class LayerManager extends CreateWindow{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	
	private ListDataProvider<LayerBean> provider = null;
	private List<LayerBean> layers;
	private MapViewer map;
	
	public LayerManager(VUI ui,MapViewer map,CloseListener listener) {
		super(ui,false);
		this.map = map ;
		initCreateWindow();
		addCloseListener(listener);
		setWidth("80%");
		setHeight("70%");
	}

	@Override
	public void saveButtonClicked() {
		LayerManager.this.close();
	}

	@Override
	public String caption() {
		return "Layer Manager";
	}

	@Override
	public ComponentContainer content() {
		
		layers = new ArrayList<>();
		
		VerticalLayout vl = new VerticalLayout() ;
		vl.setSizeFull();
		
		for(OLLayer tmp : map.getLayers()) {
			if(tmp.getClass().getSimpleName().equals("OLVectorLayer")) {
				
				OLVectorSource ol = (OLVectorSource) ((OLVectorLayer) tmp).getSource() ;
				layers.add(
					new LayerBean(
						tmp.getTitle(), 
						tmp.getClass().getSimpleName(),
						"dd",
						"" + ol.getFeatures().size(),
						true));
			}else {
				layers.add(new LayerBean(tmp.getTitle(), tmp.getClass().getSimpleName(),"dd","0",true));
			}
			
		}

		// Create a grid bound to the list
		Grid<LayerBean> grid = new Grid<>();
		grid.setItems(layers);
		grid.addColumn(LayerBean::getName).setCaption("Name");
		grid.addColumn(LayerBean::getType).setCaption("Type");
		grid.addColumn(LayerBean::getFeatures).setCaption("Features");
		grid.addColumn(LayerBean::getSource).setCaption("Source");
		grid.addColumn(LayerBean::isVisible).setCaption("Visible");
		grid.setSelectionMode(SelectionMode.MULTI) ;
		grid.setSizeFull();
		vl.addComponent(grid);
		

		addButton("Import", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				LayerManager.this.setVisible(false);
				ui.addWindow(new UploadDialog(ui,"GEOJSON", new UploadDialogCallbacks() {

					@Override
					public void onOk(VFile file,String name) {
						LayerManager.this.setVisible(true);
						
						try {
							FeatureCollection featureCollection = new ObjectMapper().readValue(file.decodedContent(), FeatureCollection.class);
							layers.add(new LayerBean(
									name, 
									"GEOJSON",
									"dd",
									"" + featureCollection.getFeatures().size(),
									true));
							
							map.drawLayer(featureCollection,name) ;
							//grid.setItems(layers);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						
					}
					
					
					@Override
					public void onCancel() {
						LayerManager.this.setVisible(true);
					}
					
				}));
			}
		});
		addDropDown("Layers", new String[] {
        	"Edit","Delete"
        }, new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				if(selectedItem.getText().equals("Edit")) {
					OLLayer lay = map.resolveLayer(new ArrayList<LayerBean>(grid.getSelectedItems()).get(0).name) ;
					OLVectorSource vls = (OLVectorSource) lay.getSource();
					
					ui.addWindow(new LayerEntry(ui,vls));
				}else if(selectedItem.getText().equals("Delete")) {
					for(LayerBean tmp : grid.getSelectedItems()) {
						layers.remove(tmp);
					}
				}
			}
		}) ;
	
		
		return vl;
	}

	@Override
	public void cancelButtonCallBack() {
		LayerManager.this.close();
	}

	protected class LayerBean implements Serializable{
		/**
		 * @author medzied
		 */
		private static final long serialVersionUID = 1L;
		private String name ;
		private String type ;
		private String source ;
		private String features ;
		private boolean visible ;
		
		
		public LayerBean(String name, String type, String source, String features,boolean visible) {
			super();
			this.name = name;
			this.type = type;
			this.source = source;
			this.features = features;
			this.visible = visible ;
		}
		
		public boolean isVisible() {
			return visible;
		}
		public void setVisible(boolean visible) {
			this.visible = visible;
		}
		public String getFeatures() {
			return features;
		}
		public void setFeatures(String features) {
			this.features = features;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		
		
	}
}
