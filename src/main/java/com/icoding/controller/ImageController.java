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

import com.icoding.domain.Media;
import com.icoding.service.ImageService;

@Controller
public class ImageController {

	@Autowired
	private ImageService imageService;

	@RequestMapping(value = { "/admin/image", "/admin/image/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		model.addAttribute("pageName", "QuaÌ‰n lyÌ? Kho");
		model.addAttribute("title", "QuaÌ‰n lyÌ? Kho");
		return "image/index";
	}

	// Response image as json
	@RequestMapping(value = "/image/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Media> getAll(Model model) {
		List<Media> listImages = new ArrayList<Media>();
		listImages = imageService.getAll();
		return listImages;
	}

	@RequestMapping(value = "/image/get", method = RequestMethod.GET)
	@ResponseBody
	public Media getImage(@RequestParam(value = "itemId") String idemId) {
		Media image = imageService.getImage(Integer.parseInt(idemId));
		return image;
	}

}
