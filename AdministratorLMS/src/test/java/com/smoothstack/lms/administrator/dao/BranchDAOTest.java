package com.smoothstack.lms.administrator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.lms.administrator.model.Branch;

@SpringBootTest
class BranchDAOTest {
	
	@Autowired
	BranchDAO brdao;
	
	@Test
	void testReadBranches() throws ClassNotFoundException, SQLException {
		for (Branch b: brdao.readBranches()) {
			brdao.deleteBranch(b.getBranchId());
		}
		assertEquals(brdao.readBranches(), new ArrayList<Branch>());
	}
	
	@Test
	void testAddBranches() throws ClassNotFoundException, SQLException {
		Branch branch = new Branch();
		branch.setBranchName("Test Branch");
		branch.setBranchAddress("Test Address");
		Integer key = brdao.addBranch(branch);
		branch.setBranchId(key);
		Branch branchFromDB = brdao.readBranchById(key);
		assertEquals(branch, branchFromDB);
	}
	
	@Test
	void testUpdateBranch() throws ClassNotFoundException, SQLException {
		Branch branch = new Branch();
		branch.setBranchName("Test Branch");
		branch.setBranchAddress("Test Address");
		Integer key = brdao.addBranch(branch);
		branch.setBranchId(key);
		branch.setBranchName("Test Branch 2");
		brdao.updateBranch(branch);
		Branch branchFromDB = brdao.readBranchById(key);
		assertEquals(branch, branchFromDB);
	}
	
	@Test
	void testDeleteBranch() throws ClassNotFoundException, SQLException {
		Branch branch = new Branch();
		branch.setBranchName("Test Branch");
		branch.setBranchAddress("Test Address");
		Integer key = brdao.addBranch(branch);
		branch.setBranchId(key);
		branch.setBranchName("Test Branch 2");
		brdao.deleteBranch(key);
		Branch branchFromDB = brdao.readBranchById(key);
		assertEquals(branchFromDB, null);
	}

}
