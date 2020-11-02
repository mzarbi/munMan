package com.nogroup.municipality.manager.data.entities;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nogroup.municipality.manager.data.converters.HashMapConverter;
import com.nogroup.municipality.manager.data.converters.MultiLangNameConverter;
import com.nogroup.municipality.manager.data.embedded.MultiLangName;

@Entity
@Table(name = "PARTNER")
public class Partner {
	
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
	
	@Column(name = "sector")
	@Convert(converter = MultiLangNameConverter.class)
    private MultiLangName sector;
	
	@Column(name = "mission")
	@Convert(converter = MultiLangNameConverter.class)
    private MultiLangName mission;
	
	@Column(name = "other",length=999999)
    @Convert(converter = HashMapConverter.class)
    private HashMap<String, Object> other = new HashMap<String, Object>();

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

	public MultiLangName getSector() {
		return sector;
	}

	public void setSector(MultiLangName sector) {
		this.sector = sector;
	}

	public MultiLangName getMission() {
		return mission;
	}

	public void setMission(MultiLangName mission) {
		this.mission = mission;
	}

	public HashMap<String, Object> getOther() {
		return other;
	}

	public void setOther(HashMap<String, Object> other) {
		this.other = other;
	}
}
