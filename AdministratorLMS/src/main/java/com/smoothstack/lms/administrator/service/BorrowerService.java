package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.dao.BorrowerDAO;
import com.smoothstack.lms.administrator.model.Borrower;

@Component
public class BorrowerService {
	
	@Autowired
	private Connection conn;
	@Autowired
	private BorrowerDAO bodao;
	
	// CREATE BORROWER
	public Integer saveBorrower(Borrower borrower) throws SQLException {
		try {
			Integer cardNo = bodao.addBorrower(borrower);
			conn.commit();
			return cardNo;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with add borrower.");
			conn.rollback();
		} 
		return null;
	}
	
	// READ BORROWERS
	public List<Borrower> readBorrowers() throws SQLException{
		try {
			return bodao.readBorrowers();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read borrowers.");
		} 
		return null;
	}
	
	// UPDATE BORROWER
	public Boolean updateBorrower(Borrower borrower) throws SQLException {
		try {
			Boolean exists = bodao.updateBorrower(borrower);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with update borrower.");
			conn.rollback();
		} 
		return null;
	}
	
	// DELETE BORROWER
	public Boolean deleteBorrower(Integer cardNo) throws SQLException {
		try {
			Boolean exists = bodao.deleteBorrower(cardNo);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with delete borrower.");
			conn.rollback();
		} 
		return null;
	}

	public Borrower getBorrowerByCardNo(Integer cardNo) throws SQLException {
		try {
			return bodao.readBorrowerCardNo(cardNo);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read borrower by card number.");
		} 
		return null;
	}	

}
