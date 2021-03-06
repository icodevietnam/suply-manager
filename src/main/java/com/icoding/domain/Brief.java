package com.icoding.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Ho So
@Entity
@Table(name = "brief")
public class Brief {

	@Id
	@GeneratedValue
	private Integer id;

	// Ngay lap ho so
	@Column(name = "createdate")
	private Date createDate;

	@Column(name = "content", length = 2000)
	private String content;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "brieftype_id")
	private BriefType briefType;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

	@Column(name = "is_displayed")
	private Boolean isShowed;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "brief")
	@JsonIgnore
	@Fetch(FetchMode.SELECT)
	private List<File> listImage;
	
	@ManyToOne
	@JoinColumn(name = "note")
	private Note note;
	
	public List<File> getListImage() {
		return listImage;
	}

	public void setListImage(List<File> listImage) {
		this.listImage = listImage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public BriefType getBriefType() {
		return briefType;
	}

	public void setBriefType(BriefType briefType) {
		this.briefType = briefType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Boolean getIsShowed() {
		return isShowed;
	}

	public void setIsShowed(Boolean isShowed) {
		this.isShowed = isShowed;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

}
