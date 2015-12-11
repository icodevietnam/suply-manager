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

import com.icoding.domain.File;
import com.icoding.service.FileService;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = { "/admin/file", "/admin/file/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		model.addAttribute("pageName", "QuaÌ‰n lyÌ� Kho");
		model.addAttribute("title", "QuaÌ‰n lyÌ� Kho");
		return "file/index";
	}

	// Response file as json
	@RequestMapping(value = "/file/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<File> getAll(Model model) {
		List<File> listFiles = new ArrayList<File>();
		listFiles = fileService.getAll();
		return listFiles;
	}


	@RequestMapping(value = "/file/get", method = RequestMethod.GET)
	@ResponseBody
	public File getFile(@RequestParam(value = "itemId") String idemId) {
		File file = fileService.getFile(Integer.parseInt(idemId));
		return file;
	}

}
