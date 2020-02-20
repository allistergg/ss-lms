package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.BorrowerDAO;
import com.smoothstack.lms.administrator.model.Borrower;

@Service
public class BorrowerService {
	
	@Autowired
	private ConnectionUtil connUtil;
	@Autowired
	private BorrowerDAO bodao;
	
	// CREATE BORROWER
	public Integer saveBorrower(Borrower borrower) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Integer cardNo = bodao.addBorrower(conn, borrower);
			conn.commit();
			return cardNo;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// READ BORROWER BY CARD NO
	public List<Borrower> getBorrowerByCardNo(Integer cardNo) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return bodao.readBorrowerCardNo(conn, cardNo);
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}	
	
	// READ BORROWERS
	public List<Borrower> readBorrowers() throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return bodao.readBorrowers(conn);
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// UPDATE BORROWER
	public Boolean updateBorrower(Borrower borrower) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = bodao.updateBorrower(conn, borrower);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// DELETE BORROWER
	public Boolean deleteBorrower(Integer cardNo) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = bodao.deleteBorrower(conn, cardNo);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}

}
