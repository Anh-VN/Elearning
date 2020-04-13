package com.myclass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;


import com.myclass.service.GenericService;

public class GenericServiceImpl<T, K> implements GenericService<T, K> {

	@Autowired
	JpaRepository<T, K> repository;
	
	@Override
	public List<T> findAll() {

		return repository.findAll();
	}

	@Override
	public T findById(K id) {
		T entity = null;

		Optional<T> result = repository.findById(id);
		
		if (result.isPresent()) {
			entity = result.get();
		}

		return entity;
	}

	@Override
	public void saveOrUpdate(T entity) {

		repository.save(entity);

	}

	@Override
	public void deleteById(K id) {

		repository.deleteById(id);
	}

}
