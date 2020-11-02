package com.nogroup.municipality.manager.ccmp.others;

import java.util.List;

import org.vaadin.addon.vol3.client.OLCoordinate;
import org.vaadin.addon.vol3.feature.OLFeature;
import org.vaadin.addon.vol3.feature.OLPolygon;
import org.vaadin.addon.vol3.source.OLVectorSource.FeatureSetChangeListener;

import com.nogroup.municipality.manager.data.embedded.VPoint;
import com.nogroup.municipality.manager.data.embedded.VPolygon;

public abstract class OnPolygonFeatureDrawn implements FeatureSetChangeListener{
	
	@Override
	public void featureAdded(OLFeature feature) {
		OLPolygon olp = (OLPolygon) feature.getGeometry() ;
		VPolygon vp = new VPolygon() ;
		for(List<OLCoordinate> tmp : olp.getElements()) {
			for(OLCoordinate tmp2 : tmp) {
				vp.add(new VPoint(tmp2.x,tmp2.y,0)) ;
			}
		}
		feature(feature.getId(),vp) ;
	}

	@Override
	public void featureDeleted(OLFeature feature) {
		
	}
	
	public abstract void feature(String id,VPolygon feature) ;

}
