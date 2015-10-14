package com.icoding.dao;

import java.util.List;

import com.icoding.domain.BriefType;

public interface BriefTypeDao {

	BriefType getBriefType(int id);

	List<BriefType> getAll();

	void saveOrUpdate(BriefType briefType);

	void delete(BriefType briefType);

	void update(BriefType briefType);
	
}
