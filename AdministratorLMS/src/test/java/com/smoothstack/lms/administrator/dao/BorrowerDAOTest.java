package com.smoothstack.lms.administrator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.lms.administrator.model.Borrower;
import com.smoothstack.lms.administrator.model.Loan;
import com.smoothstack.lms.administrator.service.ConnectionUtil;

@SpringBootTest
class BorrowerDAOTest {
	
	@Autowired
	private ConnectionUtil connUtil;
	@Autowired
	private BorrowerDAO bodao;
	
	@Test
	void testReadBorrowers() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		assertEquals(bodao.readBorrowers(conn), new ArrayList<Borrower>());
		conn.close();
	}

	@Test
	void testAddBorrowerAndReadByCardNo() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Borrower borrower = new Borrower();
		borrower.setBorrowerAddress("Test Address");
		borrower.setBorrowerName("Test Name");
		borrower.setBorrowerPhone("Test Phone");
		Integer key = bodao.addBorrower(conn, borrower);
		borrower.setCardNo(key);
		borrower.setLoans(new ArrayList<Loan>());
		Borrower borrowerDB = bodao.readBorrowerCardNo(conn, key).get(0);
		assertEquals(borrower, borrowerDB);
		conn.rollback();
		conn.close();
	}
	
	@Test
	void testUpdateBorrower() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Borrower borrower = new Borrower();
		borrower.setBorrowerAddress("Test Address");
		borrower.setBorrowerName("Test Name");
		borrower.setBorrowerPhone("Test Phone");
		Integer key = bodao.addBorrower(conn, borrower);
		borrower.setCardNo(key);
		borrower.setLoans(new ArrayList<Loan>());
		borrower.setBorrowerName("Test Borrower");
		Boolean success = bodao.updateBorrower(conn, borrower);
		assertTrue(success);
		Borrower borrowerDB = bodao.readBorrowerCardNo(conn, key).get(0);
		assertEquals(borrower, borrowerDB);
		conn.rollback();
		conn.close();
	}
	
	@Test
	void testDeleteBorrower() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Borrower borrower = new Borrower();
		borrower.setBorrowerAddress("Test Address");
		borrower.setBorrowerName("Test Name");
		borrower.setBorrowerPhone("Test Phone");
		Integer key = bodao.addBorrower(conn, borrower);
		borrower.setCardNo(key);
		bodao.deleteBorrower(conn, key);
		assertEquals(bodao.readBorrowerCardNo(conn, key).size(), 0);
		conn.rollback();
		conn.close();
	}

}
