package com.exercise.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.DisplayName;

import com.exercise.contacts.Contact;

class Test {

	@org.junit.Test
	@DisplayName(value="Test ")
	void testAdd() {
		Contact contact = new Contact("rami","delhi","334859","input/proff.jpg",LocalDate.of(1987,Month.of(2),03),"rami@gmail.com","C");
		
	}

}
