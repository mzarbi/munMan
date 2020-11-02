package com.nogroup.municipality.manager.data.embedded;

public class CMultiLangName extends MultiLangName{

	private String phone ;
	
	
	
	public CMultiLangName(String arName, String frName, String enName, String phone) {
		super(arName, frName, enName);
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return getFrName();
	}
}
