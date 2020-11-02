package com.nogroup.municipality.manager.data.embedded;

import java.util.ArrayList;

public class VPolyline extends ArrayList<VPoint>{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;

	public VPoint getCentroid() {
		double lats = 0 ;
		double lons = 0 ;
		int d = 0 ;
		for(VPoint tmp0 : this) {
			d += 1 ;
			lats += tmp0.getLatitude() ;
			lons += tmp0.getLongitude() ;
		}
		VPoint vp = new VPoint(lons/d,lats/d,0) ;
		return vp ;
	}
}
