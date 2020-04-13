package com.myclass.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.myclass.entity.Category;
import com.myclass.entity.Course;
import com.myclass.entity.Role;
import com.myclass.entity.Target;
import com.myclass.entity.User;
import com.myclass.entity.Video;
import com.myclass.service.GenericService;

public class GenericController<T> {

	private Class<T> clazz;

	@Autowired
	private GenericService<T, Integer> service;

	@PostConstruct
	private void postContructor() {
		Type[] type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();

		clazz = ((Class<T>) type[0]);

	}

	@GetMapping("")
	public Object getAll() {

		List<T> entities = service.findAll();

		if (entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<T>>(entities, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Object getById(@PathVariable int id) {

		T entity = service.findById(id);

		
		if (entity == null) {
			return new ResponseEntity<>(entity, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<T>(entity, HttpStatus.OK);
	}

	@PostMapping("")
	public Object post(@RequestBody T entity) {

		setEntityIdToZero(entity);

		service.saveOrUpdate(entity);

		return new ResponseEntity<T>(entity, HttpStatus.CREATED);

	}

	@PutMapping("")
	public Object put(@RequestBody T entity) {

		if (service.findById(findEntityId(entity)) == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		service.saveOrUpdate(entity);

		return new ResponseEntity<String>("Updated successfully", HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public Object delete(@PathVariable int id) {

		if (service.findById(id) == null) {
			return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
		}

		service.deleteById(id);

		return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);

	}

	private int findEntityId(T entity)
	{
		switch (clazz.getSimpleName()) {
		case "Role":		
		    Role role = (Role) entity;	    
			return role.getId();
		case "User":		
		    User user = (User) entity;	    
			return user.getId();
		case "Video":
			Video video = (Video) entity;
			return video.getId();
		case "Course":
			Course course = (Course) entity;
			return course.getId();
		case "Target":
			Target target = (Target) entity;
			return target.getId();
		case "Category":
			Category category = (Category) entity;
			return category.getId();
		}
		
	
	return-1;

	}

	private void setEntityIdToZero(T entity)
	{
		switch (clazz.getSimpleName()) {
		case "Role":		
		    Role role = (Role) entity;	    
			role.setId(0);
			break;
		case "User":		
		    User user = (User) entity;	    
			user.setId(0);
			break;
		case "Video":
			Video video = (Video) entity;
			video.setId(0);
			break;
		case "Course":
			Course course = (Course) entity;
			course.setId(0);
			break;
		case "Target":
			Target target = (Target) entity;
			target.setId(0);
			break;
		case "Category":
			Category category = (Category) entity;
			category.setId(0);
			break;
		}	
	
	}

}
