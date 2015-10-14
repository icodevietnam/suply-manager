package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.BriefDao;
import com.icoding.domain.Brief;
import com.icoding.domain.Customer;

@Service
@Transactional
public class BriefServiceImpl implements BriefService {

	@Autowired
	private BriefDao briefDao;

	@Override
	public List<Brief> getAll() {
		return briefDao.getAll();
	}

	@Override
	public Brief getBrief(int id) {
		return briefDao.getBrief(id);
	}

	@Override
	public void saveOrUpdate(Brief brief) {
		briefDao.saveOrUpdate(brief);
	}

	@Override
	public void delete(Brief brief) {
		briefDao.delete(brief);
	}

	@Override
	public void update(Brief brief) {
		briefDao.update(brief);
	}

	@Override
	public List<Customer> searchCustomer(String name, String email) {
		return briefDao.searchCustomer(name,email);
	}

	@Override
	public List<Brief> searchBrief(int cusId) {
		return briefDao.searchBrief(cusId);
	}

}
