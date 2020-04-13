package com.myclass.service.impl;

import org.springframework.stereotype.Service;

import com.myclass.entity.Category;
import com.myclass.service.CategoryService;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Integer> implements CategoryService{

}
