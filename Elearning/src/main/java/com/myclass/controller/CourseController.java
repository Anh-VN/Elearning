package com.myclass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Course;

@RestController
@RequestMapping("/api/course")
public class CourseController extends GenericController<Course>{

}
