package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.DocumentDao;
import com.icoding.domain.Document;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao documentDao;

	@Override
	public List<Document> getAll() {
		return documentDao.getAll();
	}

	@Override
	public Document getDocument(int id) {
		return documentDao.getDocument(id);
	}

	@Override
	public void saveOrUpdate(Document document) {
		documentDao.saveOrUpdate(document);
	}

	@Override
	public void delete(Document document) {
		documentDao.delete(document);
	}

	@Override
	public void update(Document document) {
		documentDao.update(document);
	}

}
