package com.exercise.iface;

import java.util.List;

public interface DataAccess<T> {
	
	public int addContact(T t);
	public List<T> getContactList();
	public int update(T update,String mobNo);
	public int remove(String mobNo);
	
	
}
