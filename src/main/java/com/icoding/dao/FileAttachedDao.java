package com.icoding.dao;

import java.util.List;

import com.icoding.domain.FileAttached;

public interface FileAttachedDao {

	FileAttached getFileAttached(int id);

	List<FileAttached> getAll();

	void saveOrUpdate(FileAttached fileAttached);

	void delete(FileAttached fileAttached);

	void update(FileAttached fileAttached);

}
