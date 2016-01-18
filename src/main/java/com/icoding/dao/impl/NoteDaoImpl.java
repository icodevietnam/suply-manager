package com.icoding.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.dao.NoteDao;
import com.icoding.domain.Brief;
import com.icoding.domain.Note;

@Repository
public class NoteDaoImpl implements NoteDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Note getNote(String code) {
		List<Note> noteLists = new ArrayList<Note>();
		Query query = getCurrentSession().createQuery(
				"from Note r where r.code = :code");
		query.setParameter("code", code);
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

	@Override
	public List<Note> listNoteIsNotPaid() {
		List<Note> noteLists = new ArrayList<Note>();
		Query query = getCurrentSession().createQuery(
				"from Note n where n.isPaid = :paid order by code desc ");
		query.setParameter("paid", false);
		noteLists = query.list();
		return noteLists;
	}
	
	@Override
	public List<Note> listNoteIsPaid() {
		List<Note> noteLists = new ArrayList<Note>();
		Query query = getCurrentSession().createQuery(
				"from Note n where n.isPaid = :paid order by code desc ");
		query.setParameter("paid", true);
		noteLists = query.list();
		return noteLists;
	}

	@Override
	public List<Brief> showListBriefByCode(String noteCode) {
		Note note = getNote(noteCode);
		List<Brief> listBrief = note.getListBrief();
		return listBrief;
	}

}
