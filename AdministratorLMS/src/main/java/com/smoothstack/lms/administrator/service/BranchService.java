package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.dao.BranchDAO;
import com.smoothstack.lms.administrator.model.Branch;

@Component
public class BranchService {
	
	@Autowired
	private Connection conn;
	@Autowired
	private BranchDAO brdao;
	
	// CREATE BRANCH
	public Integer saveBranch(Branch branch) throws SQLException {
		try {
			Integer branchId = brdao.addBranch(branch);
			conn.commit();
			return branchId;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with add branch.");
			conn.rollback();
		} 
		return null;
	}
	
	// READ BRANCHES
	public List<Branch> readBranches() throws SQLException{
		try {
			return brdao.readBranches();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read branches.");
		} 
		return null;
	}
	
	// UPDATE BRANCH
	public Boolean updateBranch(Branch branch) throws SQLException{
		try {
			Boolean exists = brdao.updateBranch(branch);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with update branch.");
			conn.rollback();
		} 
		return null;
	}
	
	// DELETE BRANCH
	public Boolean deleteBranch(Integer branchId) throws SQLException{
		try {
			Boolean exists = brdao.deleteBranch(branchId);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with delete branch.");
			conn.rollback();
		} 
		return null;
	}

	public Branch getBranchById(Integer branchId) throws SQLException {
		try {
			return brdao.readBranchById(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read branch by id.");
		} 
		return null;
	}	

}
