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
import com.nogroup.municipality.manager.data.converters.VFileConverter;
import com.nogroup.municipality.manager.data.converters.VPointConverter;
import com.nogroup.municipality.manager.data.embedded.MultiLangName;
import com.nogroup.municipality.manager.data.embedded.VFile;
import com.nogroup.municipality.manager.data.embedded.VPoint;

@Entity
@Table(name = "DWELLING")
public class Dwelling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "owner")
    private String owner;
	
    @Column(name = "coordinates")
    @Convert(converter = VPointConverter.class)
    private VPoint coordinates;
    
    @Column(name = "address")
	@Convert(converter = MultiLangNameConverter.class)
    private MultiLangName address;
    
    @Column(name = "dCreated")
    @Convert(converter = ShortDateConverter.class)
    private Date dCreated;
    
    @Column(name = "ownershipCertificate",length=99999)
    @Convert(converter = VFileConverter.class)
    public VFile ownershipCertificate;
    
    @Column(name = "application",length=99999)
    @Convert(converter = VFileConverter.class)
    public VFile application;
    
    @Column(name = "revenuDeclaration",length=99999)
    @Convert(converter = VFileConverter.class)
    public VFile revenuDeclaration;
    
    @Column(name = "discharge",length=99999)
    @Convert(converter = VFileConverter.class)
    public VFile discharge;
    
    @Column(name = "topographicPlan",length=99999)
    @Convert(converter = VFileConverter.class)
    public VFile topographicPlan;
    
    @Column(name = "projectDetails",length=99999)
    @Convert(converter = VFileConverter.class)
    public VFile projectDetails;
    
    @Column(name = "other",length=99999)
    @Convert(converter = HashMapConverter.class)
    private HashMap<String, Object> other = new HashMap<String, Object>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public VPoint getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(VPoint coordinates) {
		this.coordinates = coordinates;
	}

	public Date getdCreated() {
		return dCreated;
	}

	public void setdCreated(Date dCreated) {
		this.dCreated = dCreated;
	}

	public VFile getOwnershipCertificate() {
		return ownershipCertificate;
	}

	public void setOwnershipCertificate(VFile ownershipCertificate) {
		this.ownershipCertificate = ownershipCertificate;
	}

	public HashMap<String, Object> getOther() {
		return other;
	}

	public void setOther(HashMap<String, Object> other) {
		this.other = other;
	}

	public VFile getApplication() {
		return application;
	}

	public void setApplication(VFile application) {
		this.application = application;
	}

	public VFile getRevenuDeclaration() {
		return revenuDeclaration;
	}

	public void setRevenuDeclaration(VFile revenuDeclaration) {
		this.revenuDeclaration = revenuDeclaration;
	}

	public VFile getDischarge() {
		return discharge;
	}

	public void setDischarge(VFile discharge) {
		this.discharge = discharge;
	}

	public VFile getTopographicPlan() {
		return topographicPlan;
	}

	public void setTopographicPlan(VFile topographicPlan) {
		this.topographicPlan = topographicPlan;
	}

	public VFile getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(VFile projectDetails) {
		this.projectDetails = projectDetails;
	}

	public MultiLangName getAddress() {
		return address;
	}

	public void setAddress(MultiLangName address) {
		this.address = address;
	}

}
