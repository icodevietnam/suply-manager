package com.icoding.service;

import java.util.List;

import com.icoding.domain.BriefType;

public interface BriefTypeService {
	
	List<BriefType> getAll();
	BriefType getBriefType(int id);
	void saveOrUpdate(BriefType briefType);
	void delete(BriefType briefType);
	
	void update(BriefType briefType);

}
