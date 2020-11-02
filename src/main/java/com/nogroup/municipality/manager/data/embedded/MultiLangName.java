package com.nogroup.municipality.manager.data.embedded;

public class MultiLangName {

	private String arName ;
	private String frName ;
	private String enName ;
	
	public MultiLangName() {
		super();
	}
	public MultiLangName(String arName, String frName, String enName) {
		super();
		this.arName = arName;
		this.frName = frName;
		this.enName = enName;
	}
	public String getArName() {
		return arName;
	}
	public void setArName(String arName) {
		this.arName = arName;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	public String display() {
		return "" + arName + " - " + frName + " - " + enName ;
	}
}
