package com.icoding.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class File {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "path")
	private String path;

	@Column(name = "extension")
	private String extension;
	
	@Column(name = "absolutely_path")
	private String absolutelyPath;

	@ManyToOne
	@JoinColumn(name = "brief")
	private Brief brief;
	
	
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Brief getBrief() {
		return brief;
	}

	public void setBrief(Brief brief) {
		this.brief = brief;
	}

	public String getAbsolutelyPath() {
		return absolutelyPath;
	}

	public void setAbsolutelyPath(String absolutelyPath) {
		this.absolutelyPath = absolutelyPath;
	}
	
}
