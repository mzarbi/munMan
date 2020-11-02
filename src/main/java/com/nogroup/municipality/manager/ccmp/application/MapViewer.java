package com.nogroup.municipality.manager.ccmp.application;

import java.util.List;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.vaadin.addon.vol3.OLMap;
import org.vaadin.addon.vol3.OLView;
import org.vaadin.addon.vol3.OLViewOptions;
import org.vaadin.addon.vol3.client.OLCoordinate;
import org.vaadin.addon.vol3.client.Projections;
import org.vaadin.addon.vol3.client.style.OLCircleStyle;
import org.vaadin.addon.vol3.client.style.OLFillStyle;
import org.vaadin.addon.vol3.client.style.OLStrokeStyle;
import org.vaadin.addon.vol3.client.style.OLStyle;
import org.vaadin.addon.vol3.feature.OLFeature;
import org.vaadin.addon.vol3.interaction.OLDrawInteraction;
import org.vaadin.addon.vol3.interaction.OLDrawInteractionOptions.DrawingType;
import org.vaadin.addon.vol3.interaction.OLSelectInteraction;
import org.vaadin.addon.vol3.interaction.OLSelectInteraction.SelectionChangeListener;
import org.vaadin.addon.vol3.interaction.OLSelectInteractionOptions;
import org.vaadin.addon.vol3.layer.OLLayer;
import org.vaadin.addon.vol3.layer.OLTileLayer;
import org.vaadin.addon.vol3.layer.OLVectorLayer;
import org.vaadin.addon.vol3.source.OLOSMSource;
import org.vaadin.addon.vol3.source.OLSource;
import org.vaadin.addon.vol3.source.OLVectorSource;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.business.gis.ColorGenerator;
import com.nogroup.municipality.manager.business.gis.OLFeaturesUtils;
import com.nogroup.municipality.manager.ccmp.application.dlgs.FeatureDisplay;
import com.nogroup.municipality.manager.ccmp.others.OnPointFeatureDrawn;
import com.nogroup.municipality.manager.ccmp.others.OnPolygonFeatureDrawn;
import com.nogroup.municipality.manager.ccmp.others.OnVPointDrawn;
import com.nogroup.municipality.manager.ccmp.others.OnVPolygonDrawn;
import com.nogroup.municipality.manager.data.embedded.VPoint;
import com.nogroup.municipality.manager.data.embedded.VPolygon;
import com.vaadin.ui.VerticalLayout;

public class MapViewer extends VerticalLayout{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private OLMap map;
	private OLTileLayer osm;
	private VUI ui;
	
	private OLVectorLayer selectionLayer;
	private OLVectorSource selectionLayerSource;
	private OLSelectInteraction selectionInteraction;
	
	public MapViewer(VUI ui) {
		this.ui = ui ;
		
		setMargin(false);
		setSpacing(false);
		setSizeFull();
		
		map = new OLMap();
		map.setId("canvas");
		map.setView(createView());
		map.setSizeFull();
		addComponent(map);
		setExpandRatio(map, 1);
		//map.setLayerSwitcherControl(new OLLayerSwitcherControl());
		
		osm = new OLTileLayer(createTileSource());
		osm.setTitle("OSM LAYER");
		osm.setLayerVisible(true);
		map.addLayer(osm);
		
		selectionLayerSource = new OLVectorSource() ;
		selectionLayer = new OLVectorLayer(selectionLayerSource) ;
		selectionLayer.setTitle("SELCTION LAYER");
		selectionLayer.setLayerVisible(true);
		map.addLayer(selectionLayer);
		
	}

	protected OLSource createTileSource() {		
		return new OLOSMSource();
	}
	
	protected OLView createView() {
		OLViewOptions options = new OLViewOptions();
		options.setInputProjection(Projections.EPSG4326);
		OLView view = new OLView(options);
		
		view.setCenter(new OLCoordinate(9.5375,33.8869));
		view.setZoom(7);
		return view;
	}
	
	public void drawPoint(OnVPointDrawn callback) {
		OLDrawInteraction interaction;
		map.addInteraction(interaction = new OLDrawInteraction(selectionLayer, DrawingType.POINT));
		selectionLayerSource.addFeatureSetChangeListener(new OnPointFeatureDrawn() {

			@Override
			public void feature(String id,VPoint feature) {
				map.removeInteraction(interaction);
				selectionLayerSource.removeFeatureById(id);
				callback.back(id,feature);
			}
			
		});
	}
	
	public void drawPoint(OnVPointDrawn callback, boolean removeOnClose) {
		OLDrawInteraction interaction;
		map.addInteraction(interaction = new OLDrawInteraction(selectionLayer, DrawingType.POINT));
		selectionLayerSource.addFeatureSetChangeListener(new OnPointFeatureDrawn() {

			@Override
			public void feature(String id,VPoint feature) {
				map.removeInteraction(interaction);
				if(!removeOnClose) {
					selectionLayerSource.removeFeatureById(id);
				}
				callback.back(id,feature);
			}
			
		});
		
	}
	public void drawPolygon(OnVPolygonDrawn callback, boolean removeOnClose) {
		OLDrawInteraction interaction;
		map.addInteraction(interaction = new OLDrawInteraction(selectionLayer, DrawingType.POLYGON));
		selectionLayerSource.addFeatureSetChangeListener(new OnPolygonFeatureDrawn() {

			@Override
			public void feature(String id,VPolygon feature) {
				map.removeInteraction(interaction);
				if(!removeOnClose) {
					selectionLayerSource.removeFeatureById(id);
				}
				callback.back(id,feature);
			}
			
		});
	}
	
	public void drawPolygon(OnVPolygonDrawn callback) {
		OLDrawInteraction interaction;
		map.addInteraction(interaction = new OLDrawInteraction(selectionLayer, DrawingType.POLYGON));
		selectionLayerSource.addFeatureSetChangeListener(new OnPolygonFeatureDrawn() {

			@Override
			public void feature(String id,VPolygon feature) {
				map.removeInteraction(interaction);
				selectionLayerSource.removeFeatureById(id);
				callback.back(id,feature);
			}
			
		});
	}
	public void center(double lat,double lon) {
		map.getView().setCenter(lon,lat);
	}
	protected void center(VPoint vp) {
		map.getView().setCenter(vp.getLongitude(), vp.getLatitude());
	}

	public void removeFeatureFromLayer(String name, String id) {
		OLVectorLayer layer = (OLVectorLayer) map.getLayers().stream()
		  .filter(lyr -> name.equals(lyr.getTitle()))
		  .findAny()
		  .orElse(null);
		
		if(layer != null) {
			OLVectorSource src = (OLVectorSource) layer.getSource() ;
			src.removeFeatureById(id);
		}
		
	}
	
	public List<OLLayer> getLayers() {
		return map.getLayers() ;
	}

	public void drawLayer(FeatureCollection featureCollection, String name) {
		OLVectorSource vls = new OLVectorSource() ;
		OLVectorLayer vl = new OLVectorLayer(vls) ;
		//vl.setStyle(generateRandomStyles());
		vl.setTitle(name);
		vl.setLayerVisible(true);
		map.addLayer(vl);
		
		for(Feature tmp : featureCollection.getFeatures()) {
			OLFeature feature = OLFeaturesUtils.feature2olFeature(tmp) ;
			feature.getProperties().put("type", "LAYER") ;
			for(String tmp2 : tmp.getProperties().keySet()) {
				feature.getProperties().put(tmp2, "" + tmp.getProperties().get(tmp2)) ;
			}
			vls.addFeature(feature );
		}
		
		
	}

	public void setLayerVisible(String name, boolean checked) {
		for(OLLayer tmp : map.getLayers()) {
			if(tmp.getTitle().equals(name)) {
				tmp.setLayerVisible(checked);
			}
		}
	}

	public void addSelectionInteraction(boolean checked) {
		if(checked) {
			OLSelectInteractionOptions dd = new OLSelectInteractionOptions();
			dd.setLayers(map.getLayers()) ;
			
			selectionInteraction = new OLSelectInteraction(dd);
			map.addInteraction(selectionInteraction);
			
			selectionInteraction.addSelectionChangeListener(new SelectionChangeListener() {
				
				@Override
				public void selectionChanged(List<String> selectedFeatures) {
					if(selectedFeatures.size() != 0) {
						ui.addWindow(new FeatureDisplay(ui,resolveFeature(selectedFeatures.get(0))));
					}
				}
			});
		}else {
			if(selectionInteraction != null) {
				map.removeInteraction(selectionInteraction);
			}
		}
		
	}
	
	public OLFeature resolveFeature(String id) {
		for(OLLayer tmp : map.getLayers()) {
			if(tmp instanceof OLVectorLayer) {
				OLVectorLayer vl = (OLVectorLayer) tmp ;
				OLVectorSource vls = (OLVectorSource) vl.getSource() ;
				OLFeature feature = vls.getFeatureById(id) ;
				if(feature != null) {
					return feature;
				}
			}
		}
		return null;
	}
	
	public OLStyle generateRandomStyles() {
		
		String color = ColorGenerator.randomColor();
		OLStyle pointStyle = new OLStyle() ;
		pointStyle.strokeStyle = new OLStrokeStyle() ;
		pointStyle.strokeStyle.width = (double) 3 ;
		pointStyle.strokeStyle.color = "black" ;
		pointStyle.fillStyle = new OLFillStyle(color);
		pointStyle.circleStyle = new OLCircleStyle();
		pointStyle.circleStyle.fill = new OLFillStyle(color);
		return pointStyle;
	}

	public OLLayer resolveLayer(String name) {
		for(OLLayer tmp : map.getLayers()) {
			if(tmp.getTitle().equals(name)) {
				return tmp;
			}
		}
		return null;
	}

	public OLMap getMap() {
		return map;
	}

	public void addLayer(OLVectorLayer unitPositionLayer) {
		map.addLayer(unitPositionLayer);
	}
}
