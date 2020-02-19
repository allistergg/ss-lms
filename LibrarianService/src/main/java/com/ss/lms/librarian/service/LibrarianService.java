package com.ss.lms.librarian.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ss.lms.librarian.dao.BranchDAO;
import com.ss.lms.librarian.entity.Branch;
import com.ss.lms.librarian.util.ConnectionUtil;

@Component
public class LibrarianService {
	
	@Autowired
	public ConnectionUtil connUtil;
	
	@Autowired
	BranchDAO brdao;
	
	
	public List<Branch> findAllBranches() throws SQLException {
		try(Connection conn = connUtil.getConnection()) {
			return brdao.readAllBranchesFirstLevel(conn);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	
	public void updateBranch(Branch branch) throws SQLException, ClassNotFoundException {
		try(Connection conn = connUtil.getConnection()) {
			brdao.updateBranch(branch, conn);
		} 
	}
	

}
