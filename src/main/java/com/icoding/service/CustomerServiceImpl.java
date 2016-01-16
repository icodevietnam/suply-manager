package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.CustomerDao;
import com.icoding.domain.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> getAll() {
		return customerDao.getAll();
	}

	@Override
	public Customer getCustomer(String code) {
		return customerDao.getCustomer(code);
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		customerDao.saveOrUpdate(customer);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> searchCustomer(String code, String email, String name) {
		// TODO Auto-generated method stub
		return customerDao.searchCustomer(code, email, name);
	}

	@Override
	public Customer searchName(String search) {
		return customerDao.searchName(search);
	}

}
