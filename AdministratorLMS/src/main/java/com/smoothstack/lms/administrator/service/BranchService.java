package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.BranchDAO;
import com.smoothstack.lms.administrator.model.Branch;

@Service
public class BranchService {
	
	@Autowired
	private ConnectionUtil connUtil;
	@Autowired
	private BranchDAO brdao;
	
	// CREATE BRANCH
	public Integer saveBranch(Branch branch) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Integer branchId = brdao.addBranch(conn, branch);
			conn.commit();
			return branchId;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// READ BRANCH BY ID
	public List<Branch> getBranchById(Integer branchId) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return brdao.readBranchById(conn, branchId);
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}	
	
	// READ BRANCHES
	public List<Branch> readBranches() throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return brdao.readBranches(conn);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	// UPDATE BRANCH
	public Boolean updateBranch(Branch branch) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = brdao.updateBranch(conn, branch);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// DELETE BRANCH
	public Boolean deleteBranch(Integer branchId) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = brdao.deleteBranch(conn, branchId);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}

}
