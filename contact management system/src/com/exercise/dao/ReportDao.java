package com.exercise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exercise.contacts.Contact;
import com.exercise.service.ReportToFile;
import com.exercise.util.DbConnectionUtil;


public class ReportDao {

	private Connection con;
	List<Contact> list;
	ReportToFile csv;
	Scanner sc;


	public ReportDao() {
		super();
		this.con = DbConnectionUtil.getMySqlConnection();
		this.list=getContactList();
		this.sc=new Scanner(System.in);
	}

	public List<Contact> getContactList() {

		String sql="select * from contacts;";
		List<Contact> contactList=new ArrayList<>();

		try(PreparedStatement pstmt=con.prepareStatement(sql)){

			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				String name=rs.getString("name");
				String address=rs.getString("address");
				String mobNo=rs.getString("mobno");
				String profileRef=rs.getString("profileref");
				LocalDate dob = rs.getDate("dateofbirth").toLocalDate();
				String mailId=rs.getString("mailid");
				String groupName=rs.getString("groupname");
				Contact contact=new Contact(name,address,mobNo,profileRef,dob,mailId,groupName);
				contactList.add(contact);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}

		return contactList;
	}


	public void birthday(int month) {
		boolean check=true;
		for(Contact contact:list) {
			if(contact.getDateOfBirth().getMonthValue()==month) {
				System.out.println(contact);
				check=false;
			}
		}

		if(check)
			System.out.println("No birthdays in this month.");
		else {
			System.out.println("Do you want to generate the report to a file?(Y/N)");
			String report=sc.next();
			if(report.equalsIgnoreCase("Y")) {

				String data="";
				System.out.println("Enter the filename");
				String name=sc.next();
				name="result/"+name+".csv";
				csv=new ReportToFile(name);

				for(Contact contact:list) {
					if(contact.getDateOfBirth().getMonthValue()==month) {
						data+=contact.getName()+","+contact.getAddress().replaceAll(","," ")+","+
								contact.getMobNo()+","+contact.getProfileRef()+","+contact.getDateOfBirth()+","+
								contact.getMailId()+","+contact.getGroupName();
					}
				}
				csv.addData(data);
			}
		}
	}
	public void displayAll() {
		for(Contact contact:list) {
			System.out.println(contact);
		}	
		System.out.println("Do you want to generate the report to a file?(Y/N)");
		String report=sc.next();
		if(report.equalsIgnoreCase("Y")) {

			String data="";
			System.out.println("Enter file name:");
			String name=sc.next();
			name="result/"+name+".csv";
			csv=new ReportToFile(name);

			for(Contact contact:list) {

				data+=contact.getName()+","+contact.getAddress().replaceAll(","," ")+","+
						contact.getMobNo()+","+contact.getProfileRef()+","+contact.getDateOfBirth()+","+
						contact.getMailId()+","+contact.getGroupName();		
			}
			csv.addData(data);
		}	
	}


	public void groupContact(String groupname) {
		boolean check=true;
		for(Contact contact:list) {
			if(contact.getGroupName().equals(groupname)) {
				System.out.println(contact);
				check=false;
			}
		}

		if(check)
			System.out.println("No contacts in this group "+Contact.getToStringGroupName(groupname));
		else {
			System.out.println("Do you want to generate the report to a file?(Y/N)");
			String report=sc.next();
			if(report.equalsIgnoreCase("Y")) {

				String data="";
				System.out.println("Enter the filename:");
				String name=sc.next();
				name="result/"+name+".csv";
				csv=new ReportToFile(name);

				for(Contact contact:list) {
					if(contact.getGroupName().equals(groupname)) {
						data+=contact.getName()+","+contact.getAddress().replaceAll(","," ")+","+
								contact.getMobNo()+","+contact.getProfileRef()+","+contact.getDateOfBirth()+","+
								contact.getMailId()+","+contact.getGroupName();
					}
				}
				csv.addData(data);
			}
		}
	}

//refactor the method
	public void nameMobno() {
		for(Contact contact:list) {
			System.out.println(contact.getName()+"  :  "+contact.getMobNo());
		}
		System.out.println("Do you want to generate the report to a file?(Y/N)");
		String report=sc.next();
		if(report.equalsIgnoreCase("Y")) {

			String data="";
			System.out.println("Enter the filename:");
			String name=sc.next();
			name="result/"+name+".csv";
			csv=new ReportToFile(name);

			for(Contact contact:list) {
				data+=contact.getName()+"  ,  "+contact.getMobNo()+"\n";
			}
			csv.addData(data);
		}
	}


	

	public void nameEmail() {
		for(Contact contact:list) {
			System.out.println(contact.getName()+"  :  "+contact.getMailId());
		}
		System.out.println("Do you want to generate the report to a file?(Y/N)");
		String report=sc.next();
		if(report.equalsIgnoreCase("Y")) {

			String data="";
			System.out.println("Enter the filename:");
			String name=sc.next();
			name="result/"+name+".csv";
			csv=new ReportToFile(name);

			for(Contact contact:list) {
				data+=contact.getName()+"  ,  "+contact.getMailId()+"\n";
			}
			csv.addData(data);
		}

	}


	public void ascending() {

		String sql="select count(name), groupname from contacts group by groupname order by count(name);";

		try(PreparedStatement pstmt=con.prepareStatement(sql)){

			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				int count=rs.getInt("count(name)");
				String groupn=rs.getString("groupname");
				String group=Contact.getGroup(groupn);
				if(group.equals("A")) {
					System.out.println("Relatives:"+count);
					sqlGroup(groupn);
				}
				else if(group.equals("B")) {
					System.out.println("Personal friends:"+count);
					sqlGroup(groupn);
				}
				else {
					System.out.println("Professional friends:"+count);
					sqlGroup(groupn);
				}

			}

		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void sqlGroup(String groupname) {
		String sql="select * from contacts where groupname=?;";

		try(PreparedStatement pstmt=con.prepareStatement(sql)){

			pstmt.setString(1, groupname);
			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				String name=rs.getString("name");
				String address=rs.getString("address");
				String mobNo=rs.getString("mobno");
				String profileRef=rs.getString("profileref");
				LocalDate dob = rs.getDate("dateofbirth").toLocalDate();
				String mailId=rs.getString("mailid");
				String groupName=rs.getString("groupname");
				Contact contact=new Contact(name,address,mobNo,profileRef,dob,mailId,groupName);
				System.out.println(contact);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
