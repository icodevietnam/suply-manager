package com.icoding.dao;

import java.util.List;

import com.icoding.domain.Note;

public interface NoteDao {
	public Note getNote(int id);

	List<Note> getAll();

	void saveOrUpdate(Note note);

	void delete(Note note);

	void update(Note note);
}
