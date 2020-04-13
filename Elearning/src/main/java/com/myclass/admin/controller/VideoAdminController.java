package com.myclass.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.entity.Video;
import com.myclass.service.CourseService;
import com.myclass.service.VideoService;

@Controller
@RequestMapping("/admin/video")
public class VideoAdminController extends GenericAdminController<Video> {

	@Autowired
	protected VideoService videoService;

	@Autowired 
	protected CourseService courseService;
	
	
	@Override
	public String addGet(Model model) {

		model.addAttribute("video", new Video());
		model.addAttribute("courses", courseService.findAll());
		
		return "video/video-add-admin";
	}
	
	@Override
	public String editGet(Model model, @RequestParam int id) {

		model.addAttribute("video", videoService.findById(id));
		model.addAttribute("courses", courseService.findAll());
		
		return "video/video-edit-admin";
	}
}
