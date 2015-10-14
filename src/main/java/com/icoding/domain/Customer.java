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

// Khach hang
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue
	private Integer id;

	// Ten khach hang
	@Column(name = "name", length = 255)
	private String name;

	// Ngay sinh
	@Column(name = "birthdate")
	private String birthDate;

	@Column(name = "address", length = 255)
	private String address;

	@Column(name = "phone", length = 255)
	private String phone;

	@Column(name = "email", length = 255)
	private String email;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Brief> getListBriefs() {
		return listBriefs;
	}

	public void setListBriefs(List<Brief> listBriefs) {
		this.listBriefs = listBriefs;
	}

}
