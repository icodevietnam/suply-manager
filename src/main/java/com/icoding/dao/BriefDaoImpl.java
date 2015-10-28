package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.Brief;
import com.icoding.domain.Customer;

@Repository
public class BriefDaoImpl implements BriefDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Brief getBrief(int id) {
		List<Brief> briefLists = new ArrayList<Brief>();
		Query query = getCurrentSession().createQuery(
				"from Brief d where d.id = :id");
		query.setParameter("id", id);
		briefLists = query.list();
		if (briefLists.size() > 0)
			return briefLists.get(0);
		else
			return null;
	}

	@Override
	public List<Brief> getAll() {
		List<Brief> briefLists = new ArrayList<Brief>();
		Query query = getCurrentSession().createQuery(" from Brief ");
		briefLists = query.list();
		return briefLists;
	}

	@Override
	public void saveOrUpdate(Brief brief) {
		getCurrentSession().saveOrUpdate(brief);

	}

	@Override
	public void delete(Brief brief) {
		getCurrentSession().delete(brief);
	}

	@Override
	public void update(Brief brief) {
		getCurrentSession().update(brief);
	}

	@Override
	public List<Customer> searchCustomer(String code) {
		List<Customer> listCustomers = new ArrayList<Customer>();
		String sql = "";
		String fullCode = "";
		if (!code.equalsIgnoreCase("")) {
			sql = " where c.code LIKE :code ";
			fullCode = "PE"+code;
		}
		Query query = getCurrentSession().createQuery("from Customer c " + sql);
		if (!code.equalsIgnoreCase("")) {
			query.setParameter("code", "%" + fullCode + "%");
		}
		listCustomers = query.list();
		return listCustomers;
	}

	@Override
	public List<Brief> searchBrief(int cusId) {
		List<Brief> listBriefs = new ArrayList<Brief>();
		Query query = getCurrentSession().createSQLQuery(
				" Select * from Brief b where b.customer_id = :cusId ");
		query.setParameter("cusId", cusId);
		listBriefs = query.list();
		return listBriefs;
	}

}
