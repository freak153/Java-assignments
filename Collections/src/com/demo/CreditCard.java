package com.demo;

public class CreditCard implements Comparable<CreditCard> {

	private int cardNumber;
	private String cardHolderName;
	private int creditlimit;
	
	public CreditCard() {
		super();
		
	}
	public CreditCard(int cardNumber, String cardHolderName, int creditlimit) {
		super();
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.creditlimit = creditlimit;
	}
	@Override
	public String toString() {
		return "CreditCard [cardNumber=" + cardNumber + ", cardHolderName=" + cardHolderName + ", creditlimit="
				+ creditlimit + "]";
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public int getCreditlimit() {
		return creditlimit;
	}
	public void setCreditlimit(int creditlimit) {
		this.creditlimit = creditlimit;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardHolderName == null) ? 0 : cardHolderName.hashCode());
		result = prime * result + cardNumber;
		result = prime * result + creditlimit;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		if (cardHolderName == null) {
			if (other.cardHolderName != null)
				return false;
		} else if (!cardHolderName.equals(other.cardHolderName))
			return false;
		if (cardNumber != other.cardNumber)
			return false;
		if (creditlimit != other.creditlimit)
			return false;
		return true;
	}
	@Override
	public int compareTo(CreditCard otherObj) {
		// TODO Auto-generated method stub
		return this.cardHolderName.compareTo(otherObj.cardHolderName);
	}
	
}
