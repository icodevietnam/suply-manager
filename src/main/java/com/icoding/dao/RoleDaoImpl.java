package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Role getRole(int id) {
		List<Role> roleLists = new ArrayList<Role>();
		Query query = getCurrentSession().createQuery(
				"from Role r where r.id = :id");
		query.setParameter("id", id);
		roleLists = query.list();
		if (roleLists.size() > 0)
			return roleLists.get(0);
		else
			return null;
	}

	@Override
	public List<Role> getAll() {
		List<Role> listRoles = new ArrayList<Role>();
		listRoles = getCurrentSession().createCriteria(Role.class).list();
		return listRoles;
	}

	@Override
	public void saveOrUpdate(Role role) {
		getCurrentSession().saveOrUpdate(role);
	}

	@Override
	public void delete(Role role) {
		getCurrentSession().delete(role);
	}

	@Override
	public void update(Role role) {
		getCurrentSession().delete(role);
	}

}
