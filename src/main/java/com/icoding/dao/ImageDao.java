package com.icoding.dao;

import java.util.List;

import com.icoding.domain.Media;

public interface ImageDao {

	Media getImage(int id);

	List<Media> getAll();

	void saveOrUpdate(Media image);

	void delete(Media image);

	void update(Media image);

}
