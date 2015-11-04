package com.icoding.service;

import java.util.List;

import com.icoding.domain.Media;

public interface ImageService {
	
	List<Media> getAll();
	Media getImage(int id);
	void saveOrUpdate(Media image);
	void delete(Media image);
	
	void update(Media image);

}
