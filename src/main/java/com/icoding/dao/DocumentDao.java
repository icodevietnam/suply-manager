package com.icoding.dao;

import java.util.List;

import com.icoding.domain.Document;

public interface DocumentDao {

	Document getDocument(int id);

	List<Document> getAll();

	void saveOrUpdate(Document document);

	void delete(Document document);

	void update(Document document);

}
