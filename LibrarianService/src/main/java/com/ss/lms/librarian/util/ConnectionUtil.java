package com.ss.lms.librarian.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ConnectionUtil {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private String username = "root";
    private String password = "r00tR))T";

    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
    	Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
//        conn.setAutoCommit(Boolean.FALSE);
        return conn;
    }
}
