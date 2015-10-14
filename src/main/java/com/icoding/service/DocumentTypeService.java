package com.icoding.service;

import java.util.List;

import com.icoding.domain.DocumentType;

public interface DocumentTypeService {
	List<DocumentType> getAll();
	DocumentType getDocumentType(int id);
	void saveOrUpdate(DocumentType documentType);
	void delete(DocumentType documentType);
	
	void update(DocumentType documentType);
}
