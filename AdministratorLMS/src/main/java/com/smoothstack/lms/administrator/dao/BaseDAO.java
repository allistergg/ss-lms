package com.smoothstack.lms.administrator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDAO<T> {
	
	@Autowired
	protected Connection conn;
	
	protected Integer save(String sql, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null){
			int index = 1;
			for(Object o: vals){
				pstmt.setObject(index++, o);
			}
		}
		/*
		 * Execute SQL statements that insert/update/delete data at the database. 
		 * This method returns an integer value representing number of records affected.
		 */
		return pstmt.executeUpdate(); 
		
	}
	
	protected Integer saveReturnPk(String sql, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if(vals!=null){
			int index = 1;
			for(Object o: vals){
				pstmt.setObject(index, o);
				index++;
			}
		}
		pstmt.executeUpdate();

		ResultSet rs = pstmt.getGeneratedKeys();
		// retrieves the public key
		if (rs.next()){
			return rs.getInt(1);
		}
		return null;
	}
	
	// deep read
	protected List<T> read(String sql, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null){
			int index = 1;
			for(Object o: vals){
				pstmt.setObject(index++, o);
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}
	
	abstract List<T> extractData(ResultSet rs) throws SQLException, ClassNotFoundException;
	
	// shallow read
	protected List<T> readFirstLevel(String sql, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null) {
			int index = 1;
			for(Object o: vals){
				pstmt.setObject(index++, o);
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractDataFirstLevel(rs);
	}

	abstract List<T> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException;

}
