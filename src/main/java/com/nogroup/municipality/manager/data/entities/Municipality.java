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
import com.nogroup.municipality.manager.data.converters.MultiLangNameConverter;
import com.nogroup.municipality.manager.data.converters.ShortDateConverter;
import com.nogroup.municipality.manager.data.converters.VPolygonConverter;
import com.nogroup.municipality.manager.data.embedded.MultiLangName;
import com.nogroup.municipality.manager.data.embedded.VPolygon;

@Entity
@Table(name = "MUNICIPALITY")
public class Municipality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
    @Convert(converter = MultiLangNameConverter.class)
    public MultiLangName name;
	
    @Column(name = "coordinates",length=999999)
    @Convert(converter = VPolygonConverter.class)
    private VPolygon coordinates;
    
    @Column(name = "dCreated")
    @Convert(converter = ShortDateConverter.class)
    private Date dCreated;
    
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

	public VPolygon getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(VPolygon coordinates) {
		this.coordinates = coordinates;
	}

	public Date getdCreated() {
		return dCreated;
	}

	public void setdCreated(Date dCreated) {
		this.dCreated = dCreated;
	}

	public HashMap<String, Object> getOther() {
		return other;
	}

	public void setOther(HashMap<String, Object> other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "Municipality [id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", dCreated=" + dCreated
				+ ", other=" + other + "]";
	}
}
