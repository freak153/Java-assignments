package com.exercise.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import com.exercise.contacts.Contact;
import com.exercise.dao.UpdateDao;

class Testing {

	
	private UpdateDao updateDao;
		
		@BeforeEach
		public void beforeTestMethod()
		{
			updateDao=new UpdateDao();
		}
	
	@Test
	@DisplayName(value="Test to check add contact functionality ")
	void testAdd() {
		Contact contact1 = new Contact("rami","delhi","94866","input/proff.jpg",LocalDate.of(1987,Month.of(2),03),"rami@gmail.com","C");
		int rowsAdded=updateDao.addContact(contact1);
		assertEquals(1,rowsAdded);
	}
	
	@Test
	@DisplayName(value="Test to check update contact functionality ")
	void testUpdate() {
		Contact updateContact = new Contact("rami","delhi","94867","input/proff.jpg",LocalDate.of(1987,Month.of(2),03),"rami@gmail.com","C");
		String mobNo="94866";
		int rowsAdded=updateDao.update(updateContact,mobNo);
		assertEquals(1,rowsAdded);
	}

}
