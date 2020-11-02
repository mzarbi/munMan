package com.nogroup.municipality.manager.business;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import com.nogroup.municipality.manager.data.daos.UserD;
import com.nogroup.municipality.manager.data.embedded.Clearance;
import com.nogroup.municipality.manager.data.embedded.Hierarchy;
import com.nogroup.municipality.manager.data.embedded.MultiLangName;
import com.nogroup.municipality.manager.data.embedded.VFile;
import com.nogroup.municipality.manager.data.entities.User;

public class InitDatabase {

	public static void init() throws IOException {
		
		if(new UserD().count() == 0) {
			User us = new User() ;
			Clearance clearance = new Clearance() ;
			clearance.setAdmin(true);
			
			us.setClearance(clearance);
			us.setdCreated(new Date());
			us.setEmail("medzied.arbi@gmail.com");
			us.setHierarchy(Hierarchy.LEVEL1);
			us.setName(new MultiLangName("محمد زياد العربي", "Mohamed Zied Arbi", "Mohamed Zied Arbi"));
			us.setPhone("54709272");
			VFile picture = FileBundle.readFileFromResource("admin.jpeg",Charset.defaultCharset()) ;
			picture.setExtension("jpeg");
			us.setPicture(picture);
			us.setSalt(PasswordUtils.getSalt(10));
			us.setPassword(new String(PasswordUtils.hash("0000".toCharArray(), us.getSalt().getBytes())));
			
			new UserD().create(us);
		}
	}
}
