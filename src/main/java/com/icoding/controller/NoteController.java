package com.icoding.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.Brief;
import com.icoding.domain.Department;
import com.icoding.domain.Note;
import com.icoding.service.BriefService;
import com.icoding.service.DepartmentService;
import com.icoding.service.NoteService;

@Controller
public class NoteController {
	
	@Autowired
	private NoteService noteService;

	@Autowired
	private BriefService briefService;
	
	@Autowired DepartmentService departmentService;

	@RequestMapping(value = { "/admin/note",
			"admin/note/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		List<Department> listDepartments = departmentService.getAll();
		model.addAttribute("listDepartments",listDepartments);
		return "note/index";
	}
	
	@RequestMapping(value = { "/note/getNoteNoPaid"}, method = RequestMethod.GET)
	@ResponseBody
	public List<Note> getlistNoteIsNotPaid(){
		List<Note> listNotes = noteService.listNoteIsNotPaid();
		return listNotes;
	}
	
	@RequestMapping(value = { "/note/getNotePaid"}, method = RequestMethod.GET)
	@ResponseBody
	public List<Note> getlistNoteIsPaid(){
		List<Note> listNotes = noteService.listNoteIsPaid();
		return listNotes;
	}
	
	@RequestMapping(value = { "/note/saveNote"}, method = RequestMethod.POST)
	@ResponseBody
	public Note saveNote(@RequestParam(value="departmentBox") String departmentBox){
		Note note = new Note();
		try {
			Department department = departmentService.getDepartment(Integer.parseInt(departmentBox));
			note.setDepartment(department);
			note.setBorrowDate(new Date());
			
			noteService.saveOrUpdate(note);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return note;
	}
	
	@RequestMapping(value = { "/note/paidBrief"}, method = RequestMethod.POST)
	@ResponseBody
	public String paidBrief(@RequestParam(value="noteId")String noteId){
		try {
			Note note = noteService.getNote(noteId);
			List<Brief> listBriefs = note.getListBrief();
			note.setPaid(true);
			note.setPaidDate(new Date());
			noteService.saveOrUpdate(note);
			if(listBriefs.size() != 0){
				for(Brief brief : listBriefs){
					brief.setNote(null);
					briefService.saveOrUpdate(brief);
				}
			}
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
}
