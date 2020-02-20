package com.smoothstack.lms.administrator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO<T> {
	
	protected Integer save(Connection conn, String sql, Object[] vals) throws SQLException {
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
	
	protected Integer saveReturnPk(Connection conn, String sql, Object[] vals) throws SQLException {
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
	protected List<T> read(Connection conn, String sql, Object[] vals) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null){
			int index = 1;
			for(Object o: vals){
				pstmt.setObject(index++, o);
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(conn, rs);
	}
	
	abstract List<T> extractData(Connection conn, ResultSet rs) throws SQLException;
	
	// shallow read
	protected List<T> readFirstLevel(Connection conn, String sql, Object[] vals) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null) {
			int index = 1;
			for(Object o: vals){
				pstmt.setObject(index++, o);
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractDataFirstLevel(conn, rs);
	}

	abstract List<T> extractDataFirstLevel(Connection conn, ResultSet rs) throws SQLException;

}
