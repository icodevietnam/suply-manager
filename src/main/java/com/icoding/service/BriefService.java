package com.icoding.service;

import java.util.List;

import com.icoding.domain.Brief;
import com.icoding.domain.Customer;

public interface BriefService {

	List<Brief> getAll();

	Brief getBrief(int id);

	void saveOrUpdate(Brief brief);

	void delete(Brief brief);

	void update(Brief brief);

	List<Customer> searchCustomer(String name, String email);
	
	List<Brief> searchBrief(int cusId);

}
