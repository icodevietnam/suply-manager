package com.icoding.dao;

import java.util.Date;
import java.util.List;

import com.icoding.domain.Brief;
import com.icoding.domain.Customer;
import com.icoding.dto.BriefNote;
import com.icoding.dto.Graph;

public interface BriefDao {

	Brief getBrief(int id);

	List<Brief> getAll();

	void saveOrUpdate(Brief brief);

	void delete(Brief brief);

	void update(Brief brief);
	List<Customer> searchCustomer(String code);
	List<Brief> searchBrief(int cusId);
	
	List<Brief> showNoneBorrow();
	
	List<BriefNote> getListBriefNote();
	
	List<Graph> listBriefByDepartment();
	List<Graph> listGraphByStock();
	
	List<Brief> listBriefFromTo(Date fromDate,Date toDate);
	
	List<Brief> showBriefBorrow();
	
}
