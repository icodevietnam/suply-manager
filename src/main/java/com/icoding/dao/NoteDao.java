package com.icoding.dao;

import java.util.List;

import com.icoding.domain.Brief;
import com.icoding.domain.Note;

public interface NoteDao {
	public Note getNote(String code);

	List<Note> getAll();

	void saveOrUpdate(Note note);

	void delete(Note note);

	void update(Note note);
	
	List<Note> listNoteIsNotPaid();
	List<Note> listNoteIsPaid();
	
	List<Brief> showListBriefByCode(String noteCode);
}
