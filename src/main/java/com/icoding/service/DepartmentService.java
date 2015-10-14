package com.icoding.service;

import java.util.List;

import com.icoding.domain.Department;

public interface DepartmentService {
	List<Department> getAll();
	Department getDepartment(int id);
	void saveOrUpdate(Department department);
	void delete(Department department);
	
	void update(Department department);
}
