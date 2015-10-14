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

import com.icoding.domain.BriefType;
import com.icoding.service.BriefTypeService;

@Controller
public class BriefTypeController {

	@Autowired
	private BriefTypeService briefTypeService;

	@RequestMapping(value = { "/admin/briefType", "/admin/briefType/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Quản lý Loại Hồ Sơ");
		model.addAttribute("title", "Quản lý Loại Hồ Sơ");
		return "briefType/index";
	}
	
	// Response briefType as json
		@RequestMapping(value = "/briefType/getAll", method = RequestMethod.GET, produces = "application/json")
		@ResponseBody
		public List<BriefType> getAll(Model model) {
			List<BriefType> listBriefTypes = new ArrayList<BriefType>();
			listBriefTypes = briefTypeService.getAll();
			return listBriefTypes;
		}

		@RequestMapping(value = "/briefType/delete", method = RequestMethod.POST)
		@ResponseBody
		public String deleteBriefType(@RequestParam(value = "itemId") String itemId) {
			Integer id = Integer.parseInt(itemId);
			BriefType briefType = briefTypeService.getBriefType(id);
			if (briefType.getListBriefs().size() == 0) {
				briefTypeService.delete(briefType);
				return "true";
			}
			return "false";
		}

		@RequestMapping(value = "/briefType/new", method = RequestMethod.POST)
		@ResponseBody
		public String addBriefType(@RequestParam(value = "briefTypeName") String briefTypeName) {
			BriefType briefType = new BriefType();
			briefType.setName(briefTypeName);
			try {
				briefTypeService.saveOrUpdate(briefType);
				return "true";
			} catch (Exception e) {
				return "false";
			}
		}

		@RequestMapping(value = "/briefType/update", method = RequestMethod.POST)
		@ResponseBody
		public String updateBriefType(@RequestParam(value = "briefTypeId") String briefTypeId,
				@RequestParam(value = "briefTypeName") String briefTypeName) {
			BriefType briefType = briefTypeService.getBriefType(Integer.parseInt(briefTypeId));
			briefType.setName(briefTypeName);
			try {
				briefTypeService.update(briefType);
				return "true";
			} catch (Exception e) {
				return "false";
			}
		}

		@RequestMapping(value = "/briefType/get", method = RequestMethod.GET)
		@ResponseBody
		public BriefType getBriefType(@RequestParam(value = "itemId") String idemId) {
			BriefType briefType = briefTypeService.getBriefType(Integer.parseInt(idemId));
			return briefType;
		}

}
