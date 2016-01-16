package com.icoding.service;

import java.util.List;

import com.icoding.domain.Note;

public interface NoteService {
	public Note getNote(int id);

	List<Note> getAll();

	void saveOrUpdate(Note note);

	void delete(Note note);

	void update(Note note);
}
