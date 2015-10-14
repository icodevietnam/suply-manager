package com.icoding.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icoding.dao.DocumentTypeDao;
import com.icoding.domain.DocumentType;

@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService {

	@Autowired
	private DocumentTypeDao documentTypeDao;

	@Override
	public List<DocumentType> getAll() {
		return documentTypeDao.getAll();
	}

	@Override
	public DocumentType getDocumentType(int id) {
		return documentTypeDao.getDocumentType(id);
	}

	@Override
	public void saveOrUpdate(DocumentType documentType) {
		documentTypeDao.saveOrUpdate(documentType);
	}

	@Override
	public void delete(DocumentType documentType) {
		documentTypeDao.delete(documentType);
	}

	@Override
	public void update(DocumentType documentType) {
		documentTypeDao.update(documentType);
	}

}
