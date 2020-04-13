package com.myclass.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.entity.Course;
import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;

@Controller
@RequestMapping("/admin/course")
public class CourseAdminController extends GenericAdminController<Course>{

	@Autowired
	protected CourseService courseService;

	@Autowired
	protected CategoryService categoryService;
	
	
	@Override
	public String addGet(Model model) {

		model.addAttribute("course", new Course());
		model.addAttribute("categories", categoryService.findAll());
		
		return "course/course-add-admin";
	}
	
	@Override
	public String editGet(Model model, @RequestParam int id) {

		model.addAttribute("course", courseService.findById(id));
		model.addAttribute("categories", categoryService.findAll());
		
		return "course/course-edit-admin";
	}
	
}
