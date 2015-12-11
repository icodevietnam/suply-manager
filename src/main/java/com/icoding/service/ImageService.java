package com.icoding.service;

import java.util.List;

import com.icoding.domain.File;

public interface ImageService {
	
	List<File> getAll();
	File getImage(int id);
	void saveOrUpdate(File image);
	void delete(File image);
	
	void update(File image);

}
