package com.nogroup.municipality.manager.data.entities;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nogroup.municipality.manager.data.converters.ClearanceConverter;
import com.nogroup.municipality.manager.data.converters.HashMapConverter;
import com.nogroup.municipality.manager.data.converters.MultiLangNameConverter;
import com.nogroup.municipality.manager.data.converters.ShortDateConverter;
import com.nogroup.municipality.manager.data.converters.VFileConverter;
import com.nogroup.municipality.manager.data.embedded.Clearance;
import com.nogroup.municipality.manager.data.embedded.Hierarchy;
import com.nogroup.municipality.manager.data.embedded.MultiLangName;
import com.nogroup.municipality.manager.data.embedded.VFile;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	@Convert(converter = MultiLangNameConverter.class)
    private MultiLangName name;
	
	@Column(name = "email")
    private String email;
	
	@Enumerated(EnumType.STRING)
	private Hierarchy hierarchy ;
	
	@Column(name = "clearance")
	@Convert(converter = ClearanceConverter.class)
    private Clearance clearance;
	
	@Column(name = "dCreated")
    @Convert(converter = ShortDateConverter.class)
    private Date dCreated;
	
	@Column(name = "phone")
    private String phone;
	
	@Column(name = "salt")
    private String salt;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "picture",length=999999)
    @Convert(converter = VFileConverter.class)
    public VFile picture;
	
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

	public Date getdCreated() {
		return dCreated;
	}

	public void setdCreated(Date dCreated) {
		this.dCreated = dCreated;
	}

	public VFile getPicture() {
		return picture;
	}

	public void setPicture(VFile picture) {
		this.picture = picture;
	}

	public HashMap<String, Object> getOther() {
		return other;
	}

	public void setOther(HashMap<String, Object> other) {
		this.other = other;
	}

	public Hierarchy getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Hierarchy hierarchy) {
		this.hierarchy = hierarchy;
	}

	public Clearance getClearance() {
		return clearance;
	}

	public void setClearance(Clearance clearance) {
		this.clearance = clearance;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
