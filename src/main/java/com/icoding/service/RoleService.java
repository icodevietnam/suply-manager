package com.icoding.service;

import java.util.List;

import com.icoding.domain.Role;

public interface RoleService {
	public Role getRole(int id);

	List<Role> getAll();

	void saveOrUpdate(Role role);

	void delete(Role role);

	void update(Role role);
}
