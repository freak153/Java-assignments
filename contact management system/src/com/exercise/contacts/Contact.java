package com.exercise.contacts;

import java.time.LocalDate;


public class Contact {

	private String name;
	private String address;
	private String mobNo;
	private String profileRef;
	private LocalDate dateOfBirth;
	private String mailId;
	private String groupName;

	
	
	public Contact() {
		super();
		
	}


	public Contact(String name, String address, String mobNo, String profileRef, LocalDate dateOfBirth, String mailId,
			String groupName) {
		super();
		this.name = name;
		this.address = address;
		this.mobNo = mobNo;
		this.profileRef = profileRef;
		this.dateOfBirth = dateOfBirth;
		this.mailId = mailId;
		this.groupName = groupName;	
	}


	public String getName() {
		return name;
	}


	public String getAddress() {
		return address;
	}


	public String getMobNo() {
		return mobNo;
	}


	public String getProfileRef() {
		return profileRef;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public String getMailId() {
		return mailId;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}


	public void setProfileRef(String profileRef) {
		this.profileRef = profileRef;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public static String getToStringGroupName(String group) {
		if(group.equalsIgnoreCase("A")||group.equalsIgnoreCase("Relative")) {
			return "Relative";
		}else if(group.equalsIgnoreCase("B")||group.equalsIgnoreCase("Personal Friend")) {
			return"Personal Friend";
		}else if(group.equalsIgnoreCase("C")||group.equalsIgnoreCase("Professional Friend")) {
			return"Professional Friend";
		}else {
			return"none";
		}
			
	}
	
	public static String getGroup(String groupName) {
		if(groupName.equalsIgnoreCase("Relative")) {
			return "A";
		}else if(groupName.equalsIgnoreCase("Personl Friend")) {
			return"B";
		}else if(groupName.equalsIgnoreCase("Professional Friend")) {
			return"C";
		}else {
			return"none";
		}
			
	}

	@Override
	public String toString() {
		return "ContactDetails [name=" + name + ", address=" + address + ", mobNo=" + mobNo + ", profileRef=" + profileRef
				+ ", dateOfBirth=" + dateOfBirth + ", mailId=" + mailId + ", groupName=" +groupName+ "]";
	}
	
	
}
