package com.icoding.service;

import java.util.List;

import com.icoding.domain.Customer;

public interface CustomerService {
	
	List<Customer> getAll();
	Customer getCustomer(String code);
	void saveOrUpdate(Customer customer);
	void delete(Customer customer);
	
	void update(Customer customer);
	
	List<Customer> searchCustomer(String code, String email, String name);
	
	Customer searchName(String search);

}
