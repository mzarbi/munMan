package com.nogroup.municipality.manager.data.bundles;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.data.embedded.MultiLangName;

public class MultiLangBundle {
	
	HashMap<String,MultiLangName> values = null ;
	
	public MultiLangBundle() {
		readRes();
	}
	
	public MultiLangName get(String val) {
		return values.get(val) ;
	}
	
	public String get(VUI session,String val) {
		if(session.lang == SessionLang.ARABIC) {
			return values.get(val).getArName() ;
		}else if(session.lang == SessionLang.FRENCH) {
			return values.get(val).getFrName() ;
		}else if(session.lang == SessionLang.ENGLISH) {
			return values.get(val).getEnName() ;
		}
		return "SESSION LANG UNDEFINED FOR DEBUG PURPOSES ONLY" ;
	}

	public void readRes() {
		ClassLoader classLoader = getClass().getClassLoader();
		try (InputStream inputStream = classLoader.getResourceAsStream("xml/data.xml")) {
			String result = IOUtils.toString(inputStream);
			values = new ObjectMapper().readValue(result, HashMap.class) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
