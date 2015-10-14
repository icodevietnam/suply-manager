package com.icoding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.FileAttached;
import com.icoding.service.FileAttachedService;

@Controller
public class FileAttachedController {

	@Autowired
	private FileAttachedService fileAttachedService;

	@RequestMapping(value = { "/admin/fileAttached", "/admin/fileAttached/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Quản lý Kho");
		model.addAttribute("title", "Quản lý Kho");
		return "fileAttached/index";
	}

	// Response fileAttached as json
	@RequestMapping(value = "/fileAttached/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<FileAttached> getAll(Model model) {
		List<FileAttached> listFileAttacheds = new ArrayList<FileAttached>();
		listFileAttacheds = fileAttachedService.getAll();
		return listFileAttacheds;
	}


	@RequestMapping(value = "/fileAttached/get", method = RequestMethod.GET)
	@ResponseBody
	public FileAttached getFileAttached(@RequestParam(value = "itemId") String idemId) {
		FileAttached fileAttached = fileAttachedService.getFileAttached(Integer.parseInt(idemId));
		return fileAttached;
	}

}
