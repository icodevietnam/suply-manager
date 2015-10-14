package com.icoding.domain;

import java.util.List;

import javax.persistence.CascadeType;
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

// Phòng ban entity
@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", length = 255)
	private String name;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "department", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	private List<User> listUsers;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "department", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	private List<DocumentType> listDocumentTypes;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "department", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	private List<Brief> listBrief;

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

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

	public List<DocumentType> getListDocumentTypes() {
		return listDocumentTypes;
	}

	public void setListDocumentTypes(List<DocumentType> listDocumentTypes) {
		this.listDocumentTypes = listDocumentTypes;
	}

	public List<Brief> getListBrief() {
		return listBrief;
	}

	public void setListBrief(List<Brief> listBrief) {
		this.listBrief = listBrief;
	}

}
