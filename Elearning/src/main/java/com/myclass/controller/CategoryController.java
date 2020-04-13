package com.myclass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Category;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends GenericController<Category>{

}
