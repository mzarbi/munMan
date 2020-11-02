package com.nogroup.municipality.manager.data.embedded;

import java.util.List;

public class Clearance {
	private List<Module> modules ;
	private boolean admin ;

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
