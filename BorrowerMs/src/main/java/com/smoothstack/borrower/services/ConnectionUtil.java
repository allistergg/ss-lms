package com.smoothstack.borrower.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public final class  ConnectionUtil {

	private static String connectionString = "jdbc:mysql://localhost:3306/library?useSSL=false";
	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String username = "root";
	public static String password = "root";
	
	
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(connectionString, username, password);
		
	//	conn.setAutoCommit(false);
		return conn;

	}

}
