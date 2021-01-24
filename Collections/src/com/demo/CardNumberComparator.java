package com.demo;

import java.util.Comparator;

public class CardNumberComparator implements Comparator<CreditCard> {

	@Override
	public int compare(CreditCard o1, CreditCard o2) {
		
		if(o1.getCardNumber()<o2.getCardNumber())return -1;
		if(o1.getCardNumber()<o2.getCardNumber())return 1;
		
		return 0;
	}

}
