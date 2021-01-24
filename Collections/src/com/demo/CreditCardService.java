package com.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreditCardService implements DataAccess<CreditCard>{

	private List<CreditCard> cardList;
	public CreditCardService() {
		this.cardList=new ArrayList<>();
	}

	@Override
	public boolean add(CreditCard t) {

		return this.cardList.add(t);
	}

	@Override
	public CreditCard findById(int id) {

		return null;
	}

	@Override
	public List<CreditCard> findAll() {

		return this.cardList;
	}

	public List<CreditCard> sortedList() {
		Collections.sort(this.cardList);
		return this.cardList;
	}

	@Override
	public List<CreditCard> sortedList(String sortBy) {

		switch(sortBy) {

		case "cardHolderName":
			Collections.sort(this.cardList);
			break;
		case "cardNumber":
			Collections.sort(this.cardList,new CardNumberComparator());
			break;
		}

		return this.cardList;
	}

}
