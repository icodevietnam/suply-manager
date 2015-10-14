package com.icoding.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JsonIgnore
    @JoinTable(name="user_roles", 
        joinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")}
    )
    private Set<User> userRoles;

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

	public Set<User> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<User> userRoles) {
		this.userRoles = userRoles;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
