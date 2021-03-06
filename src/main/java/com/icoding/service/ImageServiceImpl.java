package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.ImageDao;
import com.icoding.domain.File;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao imageDao;

	@Override
	public List<File> getAll() {
		return imageDao.getAll();
	}

	@Override
	public File getImage(int id) {
		return imageDao.getImage(id);
	}

	@Override
	public void saveOrUpdate(File image) {
		imageDao.saveOrUpdate(image);
	}

	@Override
	public void delete(File image) {
		imageDao.delete(image);
	}

	@Override
	public void update(File image) {
		imageDao.update(image);
	}

}
