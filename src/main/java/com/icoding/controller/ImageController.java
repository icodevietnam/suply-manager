package com.icoding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.File;
import com.icoding.service.ImageService;

@Controller
public class ImageController {

	@Autowired
	private ImageService imageService;

	/*
	 * @RequestMapping(value = { "/admin/image", "/admin/image/list" }, method =
	 * RequestMethod.GET, produces = "text/plain;charset=UTF-8") public String
	 * displayPage(Model model) { model.addAttribute("pageName",
	 * "Quản ly�? Kho"); model.addAttribute("title", "Quản ly�? Kho");
	 * return "image/index"; }
	 */
	// Response image as json
	@RequestMapping(value = "/image/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<File> getAll(Model model) {
		List<File> listImages = new ArrayList<File>();
		listImages = imageService.getAll();
		return listImages;
	}

	@RequestMapping(value = "/image/get", method = RequestMethod.GET)
	@ResponseBody
	public File getImage(@RequestParam(value = "itemId") String idemId) {
		File image = imageService.getImage(Integer.parseInt(idemId));
		return image;
	}

	@RequestMapping(value = "/image/get/{imageId}", method = RequestMethod.GET)
	public String getImageFromId(Model model,@PathVariable(value = "imageId") String imageId) {
		File image = imageService.getImage(Integer.parseInt(imageId));
		model.addAttribute("image",image);
		return "image/index";
	}

}
