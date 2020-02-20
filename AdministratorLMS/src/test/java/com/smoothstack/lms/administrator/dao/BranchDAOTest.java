package com.smoothstack.lms.administrator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.lms.administrator.model.Branch;
import com.smoothstack.lms.administrator.service.ConnectionUtil;

@SpringBootTest
class BranchDAOTest {
	
	@Autowired
	private ConnectionUtil connUtil;
	@Autowired
	private BranchDAO brdao;
	
	@Test
	void testReadBranches() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		assertEquals(brdao.readBranches(conn), new ArrayList<Branch>());
		conn.close();
	}
	
	@Test
	void testAddBranches() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Branch branch = new Branch();
		branch.setBranchName("Test Branch");
		branch.setBranchAddress("Test Address");
		Integer key = brdao.addBranch(conn, branch);
		branch.setBranchId(key);
		Branch branchFromDB = brdao.readBranchById(conn, key).get(0);
		assertEquals(branch, branchFromDB);
		conn.rollback();
		conn.close();
	}
	
	@Test
	void testUpdateBranch() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Branch branch = new Branch();
		branch.setBranchName("Test Branch");
		branch.setBranchAddress("Test Address");
		Integer key = brdao.addBranch(conn, branch);
		branch.setBranchId(key);
		branch.setBranchName("Test Branch 2");
		brdao.updateBranch(conn, branch);
		Branch branchFromDB = brdao.readBranchById(conn, key).get(0);
		assertEquals(branch, branchFromDB);
		conn.rollback();
		conn.close();
	}
	
	@Test
	void testDeleteBranch() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Branch branch = new Branch();
		branch.setBranchName("Test Branch");
		branch.setBranchAddress("Test Address");
		Integer key = brdao.addBranch(conn, branch);
		branch.setBranchId(key);
		branch.setBranchName("Test Branch 2");
		brdao.deleteBranch(conn, key);
		assertEquals(brdao.readBranchById(conn, key).size(), 0);
		conn.rollback();
		conn.close();
	}

}
