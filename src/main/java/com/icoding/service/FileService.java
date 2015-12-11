package com.icoding.service;

import java.util.List;

import com.icoding.domain.File;

public interface FileService {
	
	List<File> getAll();
	File getFile(int id);
	void saveOrUpdate(File file);
	void delete(File file);
	
	void update(File file);

}
