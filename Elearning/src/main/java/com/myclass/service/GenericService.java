package com.myclass.service;

import java.util.List;

public interface GenericService<T, K> {

	public List<T> findAll();

	public T findById(K id);
	
	public void saveOrUpdate(T entity);

	public void deleteById(K id);
}
