package com.demo;

import java.util.List;

public interface DataAccess<T> {

	public boolean add(T t);
	public T findById(int id);
	public List<T> findAll();
	public List<T> sortedList(String sortBy);
}
