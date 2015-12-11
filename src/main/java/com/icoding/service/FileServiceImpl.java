package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.FileDao;
import com.icoding.domain.File;

@Service
@Transactional
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao fileDao;

	@Override
	public List<File> getAll() {
		return fileDao.getAll();
	}

	@Override
	public File getFile(int id) {
		return fileDao.getFile(id);
	}

	@Override
	public void saveOrUpdate(File file) {
		fileDao.saveOrUpdate(file);
	}

	@Override
	public void delete(File file) {
		fileDao.delete(file);
	}

	@Override
	public void update(File file) {
		fileDao.update(file);
	}

}
