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

import com.nogroup.municipality.manager.data.converters.HashMapConverter;
import com.nogroup.municipality.manager.data.converters.ShortDateConverter;
import com.nogroup.municipality.manager.data.converters.VPolylineConverter;
import com.nogroup.municipality.manager.data.embedded.Active;
import com.nogroup.municipality.manager.data.embedded.VPolyline;

@Entity
@Table(name = "DWELLING")
public class Trajectory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "dCreated")
    @Convert(converter = ShortDateConverter.class)
    private Date dCreated;
	
	@Column(name = "code")
    private String code;
	
	@Column(name = "coordinates",length=999999)
    @Convert(converter = VPolylineConverter.class)
    private VPolyline coordinates;
	
	@Enumerated(EnumType.STRING)
	private Active active ;

	@Column(name = "other",length=99999)
    @Convert(converter = HashMapConverter.class)
    private HashMap<String, Object> other = new HashMap<String, Object>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getdCreated() {
		return dCreated;
	}

	public void setdCreated(Date dCreated) {
		this.dCreated = dCreated;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public VPolyline getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(VPolyline coordinates) {
		this.coordinates = coordinates;
	}

	public Active getActive() {
		return active;
	}

	public void setActive(Active active) {
		this.active = active;
	}

	public HashMap<String, Object> getOther() {
		return other;
	}

	public void setOther(HashMap<String, Object> other) {
		this.other = other;
	}
	
	
}
