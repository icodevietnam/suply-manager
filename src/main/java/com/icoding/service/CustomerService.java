package com.icoding.service;

import java.util.List;

import com.icoding.domain.Customer;

public interface CustomerService {
	
	List<Customer> getAll();
	Customer getCustomer(int id);
	void saveOrUpdate(Customer customer);
	void delete(Customer customer);
	
	void update(Customer customer);

}
