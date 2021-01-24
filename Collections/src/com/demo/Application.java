package com.demo;

import java.util.Collection;
import java.util.List;

public class Application {
	
	public static void print(Collection<CreditCard> list) {
		for(CreditCard card:list) {
			System.out.println(card);
		}
	}

	public static void main(String[] args) {
		CreditCard c1=new CreditCard(309,"Suman",10388);
		CreditCard c2=new CreditCard(321,"Chavi",8888);
		CreditCard c3=new CreditCard(361,"Vind",5888);
		
		CreditCardService service=new CreditCardService();
		System.out.println(service.add(c1));
		System.out.println(service.add(c2));
		System.out.println(service.add(c3));
		
		List<CreditCard> list=service.findAll();
		for(CreditCard card:list) {
			System.out.println(card);
		}
		
		//print(list);
		System.out.println("---------");
		
		List<CreditCard> sortedByNumber=service.sortedList("cardNumber");
		System.out.println("Sort by cardNumber");
		print(sortedByNumber);
		
		System.out.println("---------");
		
		List<CreditCard> sortedByName=service.sortedList("cardHolderName");
		System.out.println("Sort by cardHoldername");
		print(sortedByName);
	}

}
