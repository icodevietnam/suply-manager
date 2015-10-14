package com.icoding.dao;

import java.util.List;

import com.icoding.domain.Department;

public interface DepartmentDao {

	Department getDepartment(int id);
	
	List<Department> getAll();

	void saveOrUpdate(Department department);
	
	void delete(Department department);
	
	void update(Department department);
}
