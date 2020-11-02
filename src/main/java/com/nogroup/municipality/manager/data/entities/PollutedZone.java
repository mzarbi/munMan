package com.nogroup.municipality.manager.data.entities;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nogroup.municipality.manager.data.converters.HashMapConverter;
import com.nogroup.municipality.manager.data.converters.ShortDateConverter;
import com.nogroup.municipality.manager.data.converters.VPointConverter;
import com.nogroup.municipality.manager.data.converters.VPolygonConverter;
import com.nogroup.municipality.manager.data.converters.VPolylineConverter;
import com.nogroup.municipality.manager.data.embedded.VPoint;
import com.nogroup.municipality.manager.data.embedded.VPolygon;

@Entity
@Table(name = "POLLUTED_ZONE")
public class PollutedZone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "code")
    private String code;
	
    @Column(name = "coordinates")
    @Convert(converter = VPolygonConverter.class)
    private VPolygon coordinates;
    
    @Column(name = "dCreated")
    @Convert(converter = ShortDateConverter.class)
    private Date dCreated;
    
    @Column(name = "other",length=99999)
    @Convert(converter = HashMapConverter.class)
    private HashMap<String, Object> other = new HashMap<String, Object>();


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public VPolygon getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(VPolygon coordinates) {
		this.coordinates = coordinates;
	}

	public HashMap<String, Object> getOther() {
		return other;
	}

	public void setOther(HashMap<String, Object> other) {
		this.other = other;
	}

	public Date getdCreated() {
		return dCreated;
	}

	public void setdCreated(Date dCreated) {
		this.dCreated = dCreated;
	}
}
