package com.icoding.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "path")
	private String path;

	@Column(name = "extension")
	private String extension;

	@ManyToOne
	@JoinColumn(name = "brief_id")
	private Brief brief;
}
