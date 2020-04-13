package com.myclass.admin.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.entity.Category;
import com.myclass.entity.Course;
import com.myclass.entity.Role;
import com.myclass.entity.Target;
import com.myclass.service.GenericService;
import com.myclass.service.impl.GenericServiceImpl;

public class GenericAdminController<T> {
	
	private Class<T> clazz;
	
	@Autowired
	protected GenericService<T, Integer> service;

	private String className;

	@PostConstruct
	private void postContructor()
	{
		Type[] type = ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments();
		
		clazz  = ((Class<T>) type[0]);		
		
		className = clazz.getSimpleName().toLowerCase();
		
	}
	
	@GetMapping("")
	public String getAll(Model model) {

		List<T> entities = service.findAll();
		
		model.addAttribute(className + "s", entities);

		return getFilePath("");
	}

	@GetMapping("/add")
	public String addGet(Model model) {

		model.addAttribute("entity", createEntity());
		
		return getFilePath("add");
	}

	@PostMapping("/add")
	public String addPost(Model model, @Valid @ModelAttribute("entity") T entity, BindingResult result) {

		if(result.hasErrors())
		{
			//System.out.println(result.getAllErrors());
			return getFilePath("add");
				
		}
		
		service.saveOrUpdate(entity);	
		
		return "redirect:/admin/" + className;

	}

	@GetMapping("/edit")
	public String editGet(Model model, @RequestParam int id) {

		T entity = service.findById(id);

		model.addAttribute("entity", entity);

		return getFilePath("edit");
	}

    @PostMapping("/edit")
	public String editPost(Model model,@Valid @ModelAttribute("entity") T entity, BindingResult result) {

    	if(result.hasErrors())
		{
			return getFilePath("edit");
		}
    	
    	service.saveOrUpdate(entity);

		return "redirect:/admin/" + className;
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") int id) {

		service.deleteById(id);

		return "redirect:/admin/" + className;
	}
	
	private T createEntity()
	{		
		switch (clazz.getSimpleName()) {
		case "Role":		   
			return (T) new Role();	
		case "Category":		   
			return (T) new Category();
		case "Target":		   
			return (T) new Target();
		case "Course":		   
			return (T) new Course();
			
		}
		
		return null;
	}
	
	private int getEntityId(T entity)
	{
		switch (clazz.getSimpleName()) {
		case "Role":		
		    Role role = (Role) entity;	    
			return role.getId();
		}
		
		return -1;
	}
	private String getFilePath(String mapping)
	{		
		if(mapping == "")
		{
			return className + "/" + className + "-admin";
		}
		
		return className + "/" + className + "-" + mapping + "-admin";
	}
	
    
}
