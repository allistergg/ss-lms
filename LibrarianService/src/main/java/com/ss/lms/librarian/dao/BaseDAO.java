package com.ss.lms.librarian.dao;

import java.sql.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDAO<T> {

   
	protected Integer save(String sql, Object[] vals, Connection conn) throws SQLException, ClassNotFoundException{
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
        while(rs.next()){
            return rs.getInt(1);
        }

        return null;
    }

    protected List<T> read(String sql, Object[] vals, Connection conn) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (vals != null) {
            int index = 1;
            for (Object o : vals) {
                pstmt.setObject(index, o);
                index++;
            }
        }
        ResultSet rs = pstmt.executeQuery();
        return extractData(rs, conn);
    }

    protected List<T> readFirstLevel(String sql, Object[] vals, Connection conn) throws SQLException, ClassNotFoundException{
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if(vals!=null){
            int index = 1;
            for(Object o: vals){
                pstmt.setObject(index, o);
                index++;
            }
        }
        ResultSet rs = pstmt.executeQuery();
        return extractDataFirstLevel(rs, conn);
    }

    abstract List<T> extractData(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException;
    abstract List<T> extractDataFirstLevel(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException;
}
