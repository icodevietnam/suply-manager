package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.ImageDao;
import com.icoding.domain.Media;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao imageDao;

	@Override
	public List<Media> getAll() {
		return imageDao.getAll();
	}

	@Override
	public Media getImage(int id) {
		return imageDao.getImage(id);
	}

	@Override
	public void saveOrUpdate(Media image) {
		imageDao.saveOrUpdate(image);
	}

	@Override
	public void delete(Media image) {
		imageDao.delete(image);
	}

	@Override
	public void update(Media image) {
		imageDao.update(image);
	}

}
