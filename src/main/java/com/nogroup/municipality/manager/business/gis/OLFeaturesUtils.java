package com.nogroup.municipality.manager.business.gis;

import java.util.ArrayList;
import java.util.List;

import org.geojson.Feature;
import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.geojson.Point;
import org.geojson.Polygon;
import org.vaadin.addon.vol3.client.OLCoordinate;
import org.vaadin.addon.vol3.feature.OLFeature;
import org.vaadin.addon.vol3.feature.OLLineString;
import org.vaadin.addon.vol3.feature.OLPoint;
import org.vaadin.addon.vol3.feature.OLPolygon;

public class OLFeaturesUtils {

	public static OLFeature feature2olFeature(Feature tmp) {
		OLFeature feature = new OLFeature();
		if(tmp.getGeometry().getClass().getSimpleName().equals("Polygon")) {
			OLPolygon geometry = new OLPolygon() ;
			
			for(List<LngLatAlt> tmp2 : ((Polygon)tmp.getGeometry()).getCoordinates()) {
				List<OLCoordinate> element = new ArrayList<>();
				for(LngLatAlt tmp3 : tmp2) {
					element.add(new OLCoordinate(tmp3.getLongitude(), tmp3.getLatitude())) ;
				}
				geometry.add(element );
			}				
			feature.setGeometry(geometry);
		}else if(tmp.getGeometry().getClass().getSimpleName().equals("Point")) {
			LngLatAlt c = ((Point)tmp.getGeometry()).getCoordinates() ;
			OLPoint geometry = new OLPoint(c.getLongitude(),c.getLatitude()) ;
			feature.setGeometry(geometry);
		}else if(tmp.getGeometry().getClass().getSimpleName().equals("LineString")) {
			OLLineString geometry = new OLLineString() ;
			for(LngLatAlt tmp2 : ((LineString)tmp.getGeometry()).getCoordinates()) {
				geometry.add(new OLCoordinate(tmp2.getLongitude(), tmp2.getLatitude())) ;
			}				
			feature.setGeometry(geometry);
		}
		return feature;
	}

	
}
