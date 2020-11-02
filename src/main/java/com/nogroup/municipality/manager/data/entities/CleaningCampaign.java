package com.nogroup.municipality.manager.data.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nogroup.municipality.manager.data.converters.HashMapConverter;
import com.nogroup.municipality.manager.data.converters.ListConverter;
import com.nogroup.municipality.manager.data.converters.MultiLangNameConverter;
import com.nogroup.municipality.manager.data.converters.ShortDateConverter;
import com.nogroup.municipality.manager.data.converters.VPolygonConverter;
import com.nogroup.municipality.manager.data.embedded.CampaignCollaborator;
import com.nogroup.municipality.manager.data.embedded.MultiLangName;
import com.nogroup.municipality.manager.data.embedded.VPolygon;

@Entity
@Table(name = "CLEANING_CAMPAIGN")
public class CleaningCampaign {
	
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
    
    @Column(name = "sDate")
    @Convert(converter = ShortDateConverter.class)
    private Date sDate;
    
    @Column(name = "eDate")
    @Convert(converter = ShortDateConverter.class)
    private Date eDate;
    
    @Column(name = "agents", length = 9999)
    @Convert(converter = ListConverter.class)
    private List<CampaignCollaborator> agents; 
    
    @Column(name = "sponsors", length = 9999)
    @Convert(converter = ListConverter.class)
    private List<CampaignCollaborator> sponsors; 
    
    @Column(name = "partners", length = 9999)
    @Convert(converter = ListConverter.class)
    private List<CampaignCollaborator> partners; 
    
    @Column(name = "workflow")
    private String workflow;
    
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

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public List<CampaignCollaborator> getAgents() {
		return agents;
	}

	public void setAgents(List<CampaignCollaborator> agents) {
		this.agents = agents;
	}

	public List<CampaignCollaborator> getSponsors() {
		return sponsors;
	}

	public void setSponsors(List<CampaignCollaborator> sponsors) {
		this.sponsors = sponsors;
	}

	public List<CampaignCollaborator> getPartners() {
		return partners;
	}

	public void setPartners(List<CampaignCollaborator> partners) {
		this.partners = partners;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	
}
