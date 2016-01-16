package com.icoding.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.dao.NoteDao;
import com.icoding.domain.Note;

@Repository
public class NoteDaoImpl implements NoteDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Note getNote(int id) {
		List<Note> noteLists = new ArrayList<Note>();
		Query query = getCurrentSession().createQuery(
				"from Note r where r.id = :id");
		query.setParameter("id", id);
		noteLists = query.list();
		if (noteLists.size() > 0)
			return noteLists.get(0);
		else
			return null;
	}

	@Override
	public List<Note> getAll() {
		List<Note> listNotes = new ArrayList<Note>();
		listNotes = getCurrentSession().createCriteria(Note.class).list();
		return listNotes;
	}

	@Override
	public void saveOrUpdate(Note note) {
		getCurrentSession().saveOrUpdate(note);
	}

	@Override
	public void delete(Note note) {
		getCurrentSession().delete(note);
	}

	@Override
	public void update(Note note) {
		getCurrentSession().delete(note);
	}

}
