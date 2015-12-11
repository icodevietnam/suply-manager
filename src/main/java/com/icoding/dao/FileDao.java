package com.icoding.dao;

import java.util.List;

import com.icoding.domain.File;

public interface FileDao {

	File getFile(int id);

	List<File> getAll();

	void saveOrUpdate(File file);

	void delete(File file);

	void update(File file);

}
