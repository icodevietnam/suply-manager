package com.icoding.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Loai ho so
@Entity
@Table(name = "brieftype")
public class BriefType {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", length = 255)
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "briefType")
	@JsonIgnore
	@Fetch(FetchMode.SELECT)
	private List<Brief> listBriefs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Brief> getListBriefs() {
		return listBriefs;
	}

	public void setListBriefs(List<Brief> listBriefs) {
		this.listBriefs = listBriefs;
	}
	
}
