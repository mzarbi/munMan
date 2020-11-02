package com.nogroup.municipality.manager.data.embedded;

import java.util.Date;
import java.util.List;

public class Module {
	
	private String name ;
	private List<Permission> permissions ;
	private Date expirationDate ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
