package com.myclass.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.entity.Target;
import com.myclass.service.CourseService;
import com.myclass.service.TargetService;

@Controller
@RequestMapping("/admin/target")
public class TargetAdminController extends GenericAdminController<Target>{

	@Autowired
	protected TargetService tagertService;

	@Autowired
	protected CourseService courseService;
	
	
	@Override
	public String addGet(Model model) {

		model.addAttribute("target", new Target());
		model.addAttribute("courses", courseService.findAll());
		
		return "target/target-add-admin";
	}
	
	@Override
	public String editGet(Model model, @RequestParam int id) {

		model.addAttribute("target", tagertService.findById(id));
		model.addAttribute("courses", courseService.findAll());
		
		return "target/target-edit-admin";
	}
}
