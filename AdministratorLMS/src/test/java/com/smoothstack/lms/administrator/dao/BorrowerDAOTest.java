package com.smoothstack.lms.administrator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.lms.administrator.model.Borrower;
import com.smoothstack.lms.administrator.model.Loan;

@SpringBootTest
class BorrowerDAOTest {
	
	@Autowired
	BorrowerDAO bodao;
	
//	@Test
//	void testReadBorrowers() throws ClassNotFoundException, SQLException {
//		assertEquals(bodao.readBorrowers(), new ArrayList<Borrower>());
//	}

	@Test
	void testAddBorrowerAndReadByCardNo() throws ClassNotFoundException, SQLException {
		Borrower borrower = new Borrower();
		borrower.setBorrowerAddress("Test Address");
		borrower.setBorrowerName("Test Name");
		borrower.setBorrowerPhone("Test Phone");
		Integer key = bodao.addBorrower(borrower);
		borrower.setCardNo(key);
		borrower.setLoans(new ArrayList<Loan>());
		Borrower borrowerDB = bodao.readBorrowerCardNo(key);
		assertEquals(borrower, borrowerDB);
	}
	
	@Test
	void testUpdateBorrower() throws ClassNotFoundException, SQLException {
		Borrower borrower = new Borrower();
		borrower.setBorrowerAddress("Test Address");
		borrower.setBorrowerName("Test Name");
		borrower.setBorrowerPhone("Test Phone");
		Integer key = bodao.addBorrower(borrower);
		borrower.setCardNo(key);
		borrower.setLoans(new ArrayList<Loan>());
		borrower.setBorrowerName("Test Borrower");
		Boolean success = bodao.updateBorrower(borrower);
		assertTrue(success);
		Borrower borrowerDB = bodao.readBorrowerCardNo(key);
		assertEquals(borrower, borrowerDB);
	}
	
	@Test
	void testDeleteBorrower() throws ClassNotFoundException, SQLException {
		Borrower borrower = new Borrower();
		borrower.setBorrowerAddress("Test Address");
		borrower.setBorrowerName("Test Name");
		borrower.setBorrowerPhone("Test Phone");
		Integer key = bodao.addBorrower(borrower);
		borrower.setCardNo(key);
		bodao.deleteBorrower(key);
		assertEquals(bodao.readBorrowerCardNo(key), null);
	}

}
