package com.nogroup.municipality.manager.ccmp.others;

import org.vaadin.addon.vol3.feature.OLFeature;
import org.vaadin.addon.vol3.feature.OLPoint;
import org.vaadin.addon.vol3.source.OLVectorSource.FeatureSetChangeListener;

import com.nogroup.municipality.manager.data.embedded.VPoint;

public abstract class OnPointFeatureDrawn implements FeatureSetChangeListener{

	@Override
	public void featureAdded(OLFeature feature) {
		OLPoint olp = (OLPoint) feature.getGeometry() ;
		VPoint vp = new VPoint(olp.getLongitude(),olp.getLatitude(),0) ;
		feature(feature.getId(),vp) ;
	}

	@Override
	public void featureDeleted(OLFeature feature) {
		
	}
	
	public abstract void feature(String id,VPoint feature) ;

}
