package com.nogroup.municipality.manager.data.embedded;

import java.io.Serializable;

import org.vaadin.addon.vol3.feature.OLPoint;

public class VPoint implements Serializable{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	
	private double longitude;
	private double latitude;
	
	public VPoint() {
	}

	public VPoint(double longitude, double latitude, double altitude) {
		this.longitude = longitude ;
		this.latitude = latitude ;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public OLPoint toOlPoint() {
		return new OLPoint(longitude, latitude);
	}
}
