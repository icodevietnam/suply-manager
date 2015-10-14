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

import com.icoding.domain.Department;
import com.icoding.domain.DocumentType;
import com.icoding.service.DepartmentService;
import com.icoding.service.DocumentTypeService;

@Controller
public class DocumentTypeController {

	@Autowired
	private DocumentTypeService documentTypeService;

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = { "/admin/documentType", "/admin/documentType/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		List<Department> listDepartments = departmentService.getAll();
		model.addAttribute("pageName", "Quản lý Loại đơn từ");
		model.addAttribute("title", "Quản lý Loại đơn từ");
		model.addAttribute("listDepartments", listDepartments);
		return "documentType/index";
	}
	
	// Response documentType as json
		@RequestMapping(value = "/documentType/getAll", method = RequestMethod.GET, produces = "application/json")
		@ResponseBody
		public List<DocumentType> getAll(Model model) {
			List<DocumentType> listDocumentTypes = new ArrayList<DocumentType>();
			listDocumentTypes = documentTypeService.getAll();
			return listDocumentTypes;
		}

		@RequestMapping(value = "/documentType/delete", method = RequestMethod.POST)
		@ResponseBody
		public String deleteDocumentType(@RequestParam(value = "itemId") String itemId) {
			Integer id = Integer.parseInt(itemId);
			DocumentType documentType = documentTypeService.getDocumentType(id);
			if (documentType.getListDocuments().size() == 0) {
				documentTypeService.delete(documentType);
				return "true";
			}
			return "false";
		}

		@RequestMapping(value = "/documentType/new", method = RequestMethod.POST)
		@ResponseBody
		public String addDocumentType(@RequestParam(value = "documentTypeName") String documentTypeName,
				@RequestParam(value = "departmentId") String departmentId) {
			DocumentType documentType = new DocumentType();
			documentType.setName(documentTypeName);
			Department department = departmentService.getDepartment(Integer.parseInt(departmentId));
			documentType.setDepartment(department);
			try {
				documentTypeService.saveOrUpdate(documentType);
				return "true";
			} catch (Exception e) {
				return "false";
			}
		}

		@RequestMapping(value = "/documentType/update", method = RequestMethod.POST)
		@ResponseBody
		public String updateDocumentType(@RequestParam(value = "documentTypeId") String documentTypeId,
				@RequestParam(value = "documentTypeName") String documentTypeName,
				@RequestParam(value = "departmentId") String departmentId) {
			DocumentType documentType = documentTypeService.getDocumentType(Integer.parseInt(documentTypeId));
			documentType.setName(documentTypeName);
			Department department = departmentService.getDepartment(Integer.parseInt(departmentId));
			documentType.setDepartment(department);
			try {
				documentTypeService.update(documentType);
				return "true";
			} catch (Exception e) {
				return "false";
			}
		}

		@RequestMapping(value = "/documentType/get", method = RequestMethod.GET)
		@ResponseBody
		public DocumentType getDocumentType(@RequestParam(value = "itemId") String idemId) {
			DocumentType documentType = documentTypeService.getDocumentType(Integer.parseInt(idemId));
			return documentType;
		}
}
