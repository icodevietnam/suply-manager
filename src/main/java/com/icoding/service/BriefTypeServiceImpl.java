package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.BriefTypeDao;
import com.icoding.domain.BriefType;

@Service
@Transactional
public class BriefTypeServiceImpl implements BriefTypeService {

	@Autowired
	private BriefTypeDao briefTypeDao;

	@Override
	public List<BriefType> getAll() {
		return briefTypeDao.getAll();
	}

	@Override
	public BriefType getBriefType(int id) {
		return briefTypeDao.getBriefType(id);
	}

	@Override
	public void saveOrUpdate(BriefType briefType) {
		briefTypeDao.saveOrUpdate(briefType);
	}

	@Override
	public void delete(BriefType briefType) {
		briefTypeDao.delete(briefType);
	}

	@Override
	public void update(BriefType briefType) {
		briefTypeDao.update(briefType);
	}

}
