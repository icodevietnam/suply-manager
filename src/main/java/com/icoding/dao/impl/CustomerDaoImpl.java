package com.icoding.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.dao.CustomerDao;
import com.icoding.domain.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Customer getCustomer(String code) {
		List<Customer> customerLists = new ArrayList<Customer>();
		Query query = getCurrentSession().createQuery("from Customer d where d.code = :code");
		query.setParameter("code", code);
		customerLists = query.list();
		if (customerLists.size() > 0)
			return customerLists.get(0);
		else
			return null;
	}

	@Override
	public List<Customer> getAll() {
		List<Customer> listCustomers = new ArrayList<Customer>();
		listCustomers = getCurrentSession().createCriteria(Customer.class).list();
		return listCustomers;
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		getCurrentSession().saveOrUpdate(customer);

	}

	@Override
	public void delete(Customer customer) {
		getCurrentSession().delete(customer);
	}

	@Override
	public void update(Customer customer) {
		getCurrentSession().update(customer);
	}

	@Override
	public List<Customer> searchCustomer(String code, String email, String name) {
		List<Customer> customerLists = new ArrayList<Customer>();
		Query query = getCurrentSession()
				.createQuery("from Customer c where c.code LIKE :code AND c.name LIKE :name AND c.email LIKE :email ");
		query.setParameter("code", "%" + code + "%");
		query.setParameter("name", "%" + name + "%");
		query.setParameter("email", "%" + email + "%");
		customerLists = query.list();
		return customerLists;
	}

	@Override
	public Customer searchName(String search) {
		Customer customer = null;
		Query query = getCurrentSession()
				.createQuery("from Customer c where c.code LIKE :search OR c.name LIKE :search ");
		query.setParameter("search", "%" + search + "%");
		customer = (Customer) query.list().get(0);
		return customer;
	}

}
