package com.icoding.dao;

import java.util.List;

import com.icoding.domain.DocumentType;

public interface DocumentTypeDao {

	DocumentType getDocumentType(int id);

	List<DocumentType> getAll();

	void saveOrUpdate(DocumentType documentType);

	void delete(DocumentType documentType);

	void update(DocumentType documentType);
}
