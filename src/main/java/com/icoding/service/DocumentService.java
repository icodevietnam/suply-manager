package com.icoding.service;

import java.util.List;

import com.icoding.domain.Document;

public interface DocumentService {
	
	List<Document> getAll();
	Document getDocument(int id);
	void saveOrUpdate(Document document);
	void delete(Document document);
	
	void update(Document document);

}
