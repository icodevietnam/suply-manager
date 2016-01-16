package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.NoteDao;
import com.icoding.domain.Note;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteDao noteDao;

	@Override
	public Note getNote(int id) {
		return noteDao.getNote(id);
	}

	@Override
	public List<Note> getAll() {
		return noteDao.getAll();
	}

	@Override
	public void saveOrUpdate(Note note) {
		noteDao.saveOrUpdate(note);
	}

	@Override
	public void delete(Note note) {
		noteDao.delete(note);
	}

	@Override
	public void update(Note note) {
		noteDao.update(note);
	}

}
