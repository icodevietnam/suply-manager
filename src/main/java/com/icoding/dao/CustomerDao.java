package com.icoding.dao;

import java.util.List;

import com.icoding.domain.Customer;

public interface CustomerDao {

	Customer getCustomer(String code);

	List<Customer> getAll();

	void saveOrUpdate(Customer customer);

	void delete(Customer customer);

	void update(Customer customer);
	
	List<Customer> searchCustomer(String code,String email,String name);

}
