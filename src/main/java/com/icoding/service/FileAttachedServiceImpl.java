package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.FileAttachedDao;
import com.icoding.domain.FileAttached;

@Service
@Transactional
public class FileAttachedServiceImpl implements FileAttachedService {

	@Autowired
	private FileAttachedDao fileAttachedDao;

	@Override
	public List<FileAttached> getAll() {
		return fileAttachedDao.getAll();
	}

	@Override
	public FileAttached getFileAttached(int id) {
		return fileAttachedDao.getFileAttached(id);
	}

	@Override
	public void saveOrUpdate(FileAttached fileAttached) {
		fileAttachedDao.saveOrUpdate(fileAttached);
	}

	@Override
	public void delete(FileAttached fileAttached) {
		fileAttachedDao.delete(fileAttached);
	}

	@Override
	public void update(FileAttached fileAttached) {
		fileAttachedDao.update(fileAttached);
	}

}
