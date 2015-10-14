package com.icoding.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icoding.dao.DepartmentDao;
import com.icoding.domain.Department;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> getAll() {
		return departmentDao.getAll();
	}

	@Override
	public Department getDepartment(int id) {
		return departmentDao.getDepartment(id);
	}

	@Override
	public void saveOrUpdate(Department department) {
		departmentDao.saveOrUpdate(department);
	}

	@Override
	public void delete(Department department) {
		departmentDao.delete(department);
	}

	@Override
	public void update(Department department) {
		departmentDao.update(department);
	}
	
}
