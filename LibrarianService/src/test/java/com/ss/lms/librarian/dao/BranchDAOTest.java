package com.ss.lms.librarian.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



//@SpringBootTest
//class BranchDAOTest {
//
//	@Autowired
//	BranchDAO brdao;
//	
//	Branch branch;
//	
//	@BeforeEach
//	void setUp() throws Exception {
//		
//		List<Branch> branches = brdao.readAllBranchesFirstLevel(); 
//		
//		for (Branch b : branches) {
//			brdao.deleteBranch(b);
//		}
//		branch = new Branch();
//		branch.setBranchName("43rd Street");
//		branch.setBranchAddress("200 43rd Street");
//		
//	}
//
//	@Test
//	void testAddBranch() throws ClassNotFoundException, SQLException {
//		Integer id = brdao.addBranch(branch);
//		branch.setBranchId(id);
//		assertNull(brdao.getBranchById(9999));
//		Branch branchFromDb = brdao.getBranchById(id);
//		assertEquals(branch, branchFromDb);
//		brdao.deleteBranch(branch);
//		assertNull(brdao.getBranchById(id));
//	}
//
//	@Test
//	void testUpdateBranch() throws ClassNotFoundException, SQLException {
//		Integer id = brdao.addBranch(branch);
//		branch.setBranchId(id);
//		branch.setBranchAddress("1 Wall Street");
//		brdao.updateBranch(branch);
//		Branch branchFromDb = brdao.getBranchById(id);
//		assertEquals(branchFromDb, branch);
//	}
//
//}
