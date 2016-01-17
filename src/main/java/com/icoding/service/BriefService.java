package com.icoding.service;

import java.util.List;

import com.icoding.domain.Brief;
import com.icoding.domain.Customer;
import com.icoding.dto.BriefNote;
import com.icoding.dto.Graph;

public interface BriefService {

	List<Brief> getAll();

	Brief getBrief(int id);

	void saveOrUpdate(Brief brief);

	void delete(Brief brief);

	void update(Brief brief);

	List<Customer> searchCustomer(String code);
	
	List<Brief> searchBrief(int cusId);
	
	List<Brief> showNoneBorrow();
	
	List<BriefNote> getListBriefNote();
	
	List<Graph> listBriefByDepartment();
	List<Graph> listGraphByStock();
}
