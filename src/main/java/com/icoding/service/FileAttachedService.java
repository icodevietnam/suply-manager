package com.icoding.service;

import java.util.List;

import com.icoding.domain.FileAttached;

public interface FileAttachedService {
	
	List<FileAttached> getAll();
	FileAttached getFileAttached(int id);
	void saveOrUpdate(FileAttached fileAttached);
	void delete(FileAttached fileAttached);
	
	void update(FileAttached fileAttached);

}
