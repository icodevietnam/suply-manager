package com.icoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.icoding.domain.Note;
import com.icoding.service.NoteService;

@Controller
public class NoteController {
	
	@Autowired
	private NoteService noteService;

	@RequestMapping(value = { "/admin/note",
			"admin/note/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		return "note/index";
	}
	
	@RequestMapping(value = { "/note/getAll"}, method = RequestMethod.GET)
	public List<Note> getListNote(){
		List<Note> listNotes = noteService.getAll();
		return listNotes;
	}
	
}
