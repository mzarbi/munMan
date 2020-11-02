package com.nogroup.municipality.manager.business;

import com.vaadin.server.ThemeResource;

public class ResourceBundle {

	public static ThemeResource getResource(String name,int size) {
		String path = "img/pic" + size + "px/" + name ;
		System.out.println(path);
		return new ThemeResource(path);
	}
}
