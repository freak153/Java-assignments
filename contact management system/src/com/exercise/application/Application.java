package com.exercise.application;

import java.util.Scanner;

import com.exercise.service.Service;

public class Application {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		//ReportService report =new ReportService();
		Service service=new Service(); 

		do {
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("				Contact Management System			");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("1)Add contact(s)\n2)Update contact(s)\n3)Remove contact(s)\n4)Display contact(s)\n"
					+ "5)Read a csv file and add the records\n"
					+ "6)Generate report for Birthdays in month\n7)Generate report for list of Contact by Group\n"
					+ "8)Generate report for list of Contacts with their name and email id\n"
					+ "9)Generate report for list of Contacts with their name and mob no\n"
					+ "10)Generate report for grouping contacts by groupname and\n "
					+ "  listing in ascending order of the count on the groups\n"
					+ "11)Exit");
			int option = sc.nextInt();

			switch(option) {
			case 1:{
				service.add();;
				break;
			}
			case 2:{
				service.update();
				break;
				
			}
			case 3:{
				service.remove();
				break;
			}
			case 4:{
				service.distpayContacts();
				break;
			}
			case 5:{
				String fileName="";
				System.out.println("Enter the filename");
				fileName="input/"+sc.next()+".csv";
				service.readFromCsvFile(fileName);
				break;
			}
			case 6:{
				service.birthday();
				break;
			}
			case 7:{
				service.group();
				break;
			}
			case 8:{
				service.nameEmail();
				break;
				
			}
			case 9:{
				service.nameMobno();
				break;
			}
			case 10:{
				service.ascending();
				break;
			}
			case 11:{
				sc.close();
				System.exit(0);
			}
			default:{
				System.out.println("Invalid option");
			}

			}

		} while (true);


	}

}
