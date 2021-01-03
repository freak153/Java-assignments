package com.exercise.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnectionUtil {
	
	public static Connection getMySqlConnection()  {
		
		Connection con = null;
		String fileName="DbConnection.properties";
				
		try {
			InputStream inStream=DbConnectionUtil.class.getClassLoader().getResourceAsStream(fileName);
			Properties props=new Properties();
			props.load(inStream);
			
			con=DriverManager.getConnection(props.getProperty("datasource.url"),
					props.getProperty("datasource.username"),
					props.getProperty("datasource.password")
					);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return con;
	}
}
