package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.RoleDao;
import com.icoding.domain.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role getRole(int id) {
		return roleDao.getRole(id);
	}

	@Override
	public List<Role> getAll() {
		return roleDao.getAll();
	}

	@Override
	public void saveOrUpdate(Role role) {
		roleDao.saveOrUpdate(role);
	}

	@Override
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

}
