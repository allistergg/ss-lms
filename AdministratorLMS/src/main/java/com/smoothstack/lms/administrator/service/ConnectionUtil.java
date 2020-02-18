package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConnectionUtil {
	
	// change to environment variables
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/library?useSSL=false&allowPublicKeyRetrieval=true";
	private static String username = "root";
	private static String password = "r00tR))T";
	
	@Bean
	protected Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}

}
