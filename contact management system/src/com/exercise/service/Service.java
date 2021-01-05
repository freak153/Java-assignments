package com.exercise.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.exercise.contacts.Contact;
import com.exercise.dao.ReportDao;
import com.exercise.dao.UpdateDao;


public class Service {

	UpdateDao updateDao;
	ReportDao reportDao;
	
	boolean ret=true;
	boolean flag=true;

	Scanner sc;

	public Service() {
		super();
		this.sc=new Scanner(System.in);
		this.updateDao=new UpdateDao();
		
	}


	public void readFromCsvFile(String fileName) {

		String line = "";  
		String splitBy = ",";  
		try   
		{  

			BufferedReader reader = new BufferedReader(new FileReader(fileName));  
			while ((line = reader.readLine()) != null)
			{  
				String[] contactDetails = line.split(splitBy);    
				String name=contactDetails[0];
				String address=contactDetails[1];
				String mobNo=contactDetails[2];
				String profileRef=contactDetails[3];
				LocalDate dateOfBirth=null;

				String dob=contactDetails[4];
				String[] DOB=dob.split("-");

				int date=Integer.parseInt(DOB[0]);
				int month=Integer.parseInt(DOB[1]);
				int year=Integer.parseInt(DOB[2]);

				dateOfBirth = LocalDate.of(year, Month.of(month), date);

				String email=contactDetails[5];
				String group=contactDetails[6];
				Contact contact=null;
				boolean check=true;
				try{
					contact=new Contact(name, address, mobNo, profileRef, dateOfBirth, email, group);
				}catch(ClassCastException | InputMismatchException  e) {
					System.out.println("Your input in csv file is invalid, please try again");
					check=false;
				}
				if(check==true) {
					System.out.println("Rows updated is:" + updateDao.addContact(contact));
				}

			}  

			reader.close();
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}
	}


	
	public void add() {
		boolean flag=true;
		do {
			flag=true;
			Contact contact = enterDetails();
			if(contact==null) {
				System.out.println("Rows updated = 0");
			}
			else {
				System.out.println("Rows updated = " + updateDao.addContact(contact));
			}
			System.out.println("Do you want to continue adding? (Y/N)");
			sc.nextLine();
			String cont=sc.nextLine();
			if(cont.equalsIgnoreCase("N"))
				flag=false;
		} while (flag);

	}


	public Contact enterDetails() {
		String name="";
		String address="";
		String mobNo="";
		String profileRef="";
		LocalDate dateOfBirth=null;
		String email="";
		String group="";
		Contact contact=null;
		boolean check=true;
		try {
			System.out.println("Enter name");
			name = sc.next();
			System.out.println("Enter address");
			address = sc.next();
			System.out.println("Enter mobile number");
			mobNo = sc.next();
			System.out.println("Enter path to profile picture");
			profileRef = sc.next();
			System.out.println("Enter date of birth as follows-");
			System.out.println("date(dd)");
			int date = sc.nextInt();
			System.out.println("month(mm)");
			int month = sc.nextInt();
			System.out.println("year(yyyy)");  
			int year = sc.nextInt();

			dateOfBirth = LocalDate.of(year, Month.of(month), date);
			System.out.println("Enter email id:");
			email = sc.next();
			System.out.println(
					"Enter group option- A)Relative:\n B)Personal Friend:\n C)Professional Friend:");
			group = sc.next();
			group=Contact.getToStringGroupName(group);
		} catch (ClassCastException | InputMismatchException  e) {

			System.out.println("Your input is invalid, please try again");
			check=false;

		}
		if(check==true)
			contact = new Contact(name, address, mobNo, profileRef, dateOfBirth, email, group);

		return contact;
	}

	public void update() {
		do {
			flag=true;
			System.out.println("Enter the mobile number of the contact which is to be updated");
			String number = sc.next();
			int row = 0;
			if (updateDao.checkMobNo(number)) {
				
				Contact contact = enterDetails();
				row = updateDao.update( contact,number);
				System.out.println("Done.");
				System.out.println("Number of rows updated = " + row);
			} else {
				System.out.println("This contact not found.");
			} 

			System.out.println("Do you want to continue updating?(Y/N)");
			String cont = sc.next();
			if (cont.equalsIgnoreCase("N"))
				flag = false;
		} while (flag);

	}
	

	public void remove() {
		do {
			flag=true;
			System.out.println("Enter the mobile number of the contact which is to be deleted");
			String number = sc.next();
			int row = 0;
			if (updateDao.checkMobNo(number)) {
				
				row=updateDao.remove(number);
				System.out.println("Done.");
				System.out.println("Number of rows updated = " + row);
			} else {
				System.out.println("This contact not found");
			} 

			System.out.println("Do you want to continue deleting contacts?(Y/N)");
			String cont = sc.next();
			if (cont.equalsIgnoreCase("N"))
				flag = false;
		} while (flag);
	}



	public void distpayContacts() {

		List<Contact> list=new ArrayList<>();

		list=updateDao.getContactList();
		for(Contact contact:list) {
			System.out.println(contact);
		}
		
	}

	public void birthday() {
		this.reportDao=new ReportDao();
		do {
    		flag=true;
			System.out.println("Enter the birthday month(1-12) ");
			int number = sc.nextInt();
			if (number<=12) {
				reportDao.birthday(number);
			} else {
				System.out.println("Invalid option");
			} 
			
			System.out.println("Do you want to try out different month? (Y/N)");
			String cont = sc.next();
			if (cont.equalsIgnoreCase("N"))
				flag = false;
		} while (flag);
    }
		
	
	public void group() {
		this.reportDao=new ReportDao();
		do {
    		
			System.out.println("A)Relative\nB)Personl friends\nC)Professional friends");
			String group = sc.next();
			flag=true;
			if (group.equals("A")||group.equals("B")||group.equals("C")) {
				String groupn=Contact.getToStringGroupName(group);
				reportDao.groupContact(groupn);
			} else {
				System.out.println("Invalid option");
			} 
			
			System.out.println("Do you want to try out a different entry?(Y/N)");
			String cont = sc.next();
			if (cont.equalsIgnoreCase("N"))
				flag = false;
		} while (flag);
    
	}
	
	public void nameMobno() {
		this.reportDao=new ReportDao();
		reportDao.nameMobno();
	}


	public void nameEmail() {
		this.reportDao=new ReportDao();
		reportDao.nameEmail();
	}


	public void ascending() {
		this.reportDao=new ReportDao();
		reportDao.ascending();

	}

	

}
