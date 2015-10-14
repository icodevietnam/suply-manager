package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAll() {
		List<Department> listDepartments = new ArrayList<Department>();
		listDepartments = getCurrentSession().createCriteria(Department.class)
				.list();
		return listDepartments;
	}

	@Override
	public Department getDepartment(int id) {
		List<Department> departmentLists = new ArrayList<Department>();
		Query query = getCurrentSession().createQuery(
				"from Department d where d.id = :id");
		query.setParameter("id", id);
		departmentLists = query.list();
		if (departmentLists.size() > 0)
			return departmentLists.get(0);
		else
			return null;
	}

	@Override
	public void saveOrUpdate(Department department) {
		try {
			getCurrentSession().saveOrUpdate(department);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Department department) {
		try {
			getCurrentSession().delete(department);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Department department) {
		try {
			getCurrentSession().update(department);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
