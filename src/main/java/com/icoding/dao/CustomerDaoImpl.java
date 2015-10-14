package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.Customer;
import com.icoding.domain.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Customer getCustomer(int id) {
		List<Customer> customerLists = new ArrayList<Customer>();
		Query query = getCurrentSession().createQuery(
				"from Customer d where d.id = :id");
		query.setParameter("id", id);
		customerLists = query.list();
		if (customerLists.size() > 0)
			return customerLists.get(0);
		else
			return null;
	}

	@Override
	public List<Customer> getAll() {
		List<Customer> listCustomers = new ArrayList<Customer>();
		listCustomers = getCurrentSession().createCriteria(Customer.class)
				.list();
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

}
