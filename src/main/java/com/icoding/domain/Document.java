package com.icoding.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Don tu
@Entity
@Table(name = "document")
public class Document {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "createdated")
	private Date createdDate;

	@Column(name = "content", length = 2000)
	private String content;

	@ManyToOne
	@JoinColumn(name = "documenttype_id")
	private DocumentType documentType;

	@ManyToOne
	@JoinColumn(name = "brief_id")
	private Brief brief;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "document")
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	private FileAttached fileAttached;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public Brief getBrief() {
		return brief;
	}

	public void setBrief(Brief brief) {
		this.brief = brief;
	}

	public FileAttached getFileAttached() {
		return fileAttached;
	}

	public void setFileAttached(FileAttached fileAttached) {
		this.fileAttached = fileAttached;
	}

}
