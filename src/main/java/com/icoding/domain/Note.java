package com.icoding.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "note")
public class Note {

	@Id
	@GenericGenerator(name = "seq_note_code", strategy = "com.icoding.generator.NoteGenerator")
	@GeneratedValue(generator = "seq_note_code", strategy = GenerationType.SEQUENCE)
	@Column(name = "code", unique = true, nullable = false, length = 20)
	private String code;

	@ManyToOne
	@JoinColumn(name = "department")
	private Department department;

	@Column(name = "borrow_date")
	private Date borrowDate;
	
	@Column(name = "paid_date")
	private Date paidDate;
	
	@Column(name="is_paid")
	private boolean isPaid = false;
	
	@Column(name="borrow_man")
	private String borrowMan;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "note", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	private List<Brief> listBrief;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public List<Brief> getListBrief() {
		return listBrief;
	}

	public void setListBrief(List<Brief> listBrief) {
		this.listBrief = listBrief;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String getBorrowMan() {
		return borrowMan;
	}

	public void setBorrowMan(String borrowMan) {
		this.borrowMan = borrowMan;
	}

}
