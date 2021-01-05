package com.exercise.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exercise.contacts.Contact;
import com.exercise.iface.DataAccess;
import com.exercise.util.DbConnectionUtil;


public class UpdateDao implements DataAccess<Contact>{
	
	private Connection con;
	

	public UpdateDao() {
		super();
		this.con = DbConnectionUtil.getMySqlConnection();
	}

	@Override
	public int addContact(Contact contact) {

		String sql="insert into contacts values(?,?,?,?,?,?,?);";
		int row=0;
		
        try(PreparedStatement pstmt=con.prepareStatement(sql)){
			
        	pstmt.setString(1, contact.getName());
        	pstmt.setString(2, contact.getAddress());
        	pstmt.setString(3, contact.getMobNo());
        	pstmt.setString(4, contact.getProfileRef());
        	pstmt.setDate(5, Date.valueOf(contact.getDateOfBirth()));
        	pstmt.setString(6, contact.getMailId());
        	pstmt.setString(7, Contact.getToStringGroupName(contact.getGroupName()));
        	
        	
			row =pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return row;
	}

	@Override
	public List<Contact> getContactList() {
		
		String sql="select * from contacts;";
			
		List<Contact> contactList=new ArrayList<>();
		
		try(PreparedStatement pstmt=con.prepareStatement(sql)){
			
			ResultSet rs=pstmt.executeQuery();
			System.out.println(rs);
			
			while(rs.next()) {
				String name=rs.getString("name");
				String address=rs.getString("address");
				String number=rs.getString("mobno");
				String profileRef=rs.getString("profileref");
				LocalDate dob = rs.getDate("dateofbirth").toLocalDate();
				String mailId=rs.getString("mailid");
				String groupName=rs.getString("groupname");
				groupName=Contact.getToStringGroupName(groupName);
				Contact contact=new Contact(name,address,number,profileRef,dob,mailId,groupName);
				contactList.add(contact);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return contactList;
	}

	@Override
	public int update( Contact contact,String mobNo) {
		

		int row=0;
	    	String sql="update contacts set name=?,address=?,mobno=?,profileref=?,dateofbirth=?,mailid=?,groupname=? where mobno=?;";
	    	try(PreparedStatement pstmt=con.prepareStatement(sql)){
				
	        	pstmt.setString(1, contact.getName());
	        	pstmt.setString(2, contact.getAddress());
	        	pstmt.setString(3, contact.getMobNo());
	        	pstmt.setString(4, contact.getProfileRef());
	        	pstmt.setDate(5, Date.valueOf(contact.getDateOfBirth()));
	        	pstmt.setString(6, contact.getMailId());
	        	pstmt.setString(7, Contact.getToStringGroupName(contact.getGroupName()));
	        	pstmt.setString(8, mobNo);
	        	
	        	
				row =pstmt.executeUpdate();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
		return row;
	}
	
	
    public boolean checkMobNo(String mobno) {
    	boolean present=false;
		List<Contact> list=new ArrayList<>();
		UpdateDao updateDao =new UpdateDao();
	    list=updateDao.getContactList();
	    for(Contact contact:list) {
	    	if(contact.getMobNo().equals(mobno))
	    		present=true;
	    }
	    return present;
	}

	@Override
	public int remove(String mobno) {
		String sql="delete from contacts where mobno=?;";
		
		int row=0;
    	
    	try(PreparedStatement pstmt=con.prepareStatement(sql)){
        	
    		pstmt.setString(1, mobno);
			row =pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
	return row;

	}



}
