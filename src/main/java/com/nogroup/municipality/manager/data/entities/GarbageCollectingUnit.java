package com.nogroup.municipality.manager.data.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.nogroup.municipality.manager.data.converters.ListCMultiNameConverter;
import com.nogroup.municipality.manager.data.converters.ShortDateConverter;
import com.nogroup.municipality.manager.data.converters.TPointConverter;
import com.nogroup.municipality.manager.data.embedded.CMultiLangName;
import com.nogroup.municipality.manager.data.embedded.GCUType;

@Entity
@Table(name = "GARBAGE_COLLECTING_UNIT")
public class GarbageCollectingUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "dCreated")
    @Convert(converter = ShortDateConverter.class)
    private Date dCreated;
	
	@Column(name = "code")
    private String code;
	
	@Enumerated(EnumType.STRING)
	private GCUType type ;
	
	@Column(name = "agents")
	@Convert(converter = ListCMultiNameConverter.class)
    private List<CMultiLangName> agents;
	
	@Column(name = "lastKnownLocation")
    @Convert(converter = TPointConverter.class)
    private TPointConverter lastKnownLocation;
	
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
	public List<CMultiLangName> getAgents() {
		return agents;
	}

	public void setAgents(List<CMultiLangName> agents) {
		this.agents = agents;
	}
	public TPointConverter getLastKnownLocation() {
		return lastKnownLocation;
	}

	public void setLastKnownLocation(TPointConverter lastKnownLocation) {
		this.lastKnownLocation = lastKnownLocation;
	}
	public HashMap<String, Object> getOther() {
		return other;
	}
	public void setOther(HashMap<String, Object> other) {
		this.other = other;
	}

	public GCUType getType() {
		return type;
	}

	public void setType(GCUType type) {
		this.type = type;
	}
	
	
	
}
