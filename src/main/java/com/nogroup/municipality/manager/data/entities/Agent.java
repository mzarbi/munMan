package com.nogroup.municipality.manager.data.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nogroup.municipality.manager.data.converters.MultiLangNameConverter;
import com.nogroup.municipality.manager.data.embedded.MultiLangName;

public class Agent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	@Convert(converter = MultiLangNameConverter.class)
    private MultiLangName name;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "phone")
    private String phone;
	
	@Column(name = "role")
	@Convert(converter = MultiLangNameConverter.class)
    private MultiLangName role;
	
	@Column(name = "municipality")
    private String municipality;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MultiLangName getName() {
		return name;
	}

	public void setName(MultiLangName name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public MultiLangName getRole() {
		return role;
	}

	public void setRole(MultiLangName role) {
		this.role = role;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
}
