package com.icoding.dao;

import java.util.List;

import com.icoding.domain.File;

public interface ImageDao {

	File getImage(int id);

	List<File> getAll();

	void saveOrUpdate(File image);

	void delete(File image);

	void update(File image);

}
