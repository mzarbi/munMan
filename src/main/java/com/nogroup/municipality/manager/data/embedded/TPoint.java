package com.nogroup.municipality.manager.data.embedded;

import java.util.Date;

public class TPoint extends VPoint{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private Date date ;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
