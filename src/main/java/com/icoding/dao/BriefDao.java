package com.icoding.dao;

import java.util.List;

import com.icoding.domain.Brief;
import com.icoding.domain.Customer;

public interface BriefDao {

	Brief getBrief(int id);

	List<Brief> getAll();

	void saveOrUpdate(Brief brief);

	void delete(Brief brief);

	void update(Brief brief);
	List<Customer> searchCustomer(String name, String email);
	List<Brief> searchBrief(int cusId);
}
