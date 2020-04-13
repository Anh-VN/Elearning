package com.myclass.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Category;

@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController extends GenericAdminController<Category> {

}
