package com.smoothstack.lms.administrator.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.model.Branch;

@Component
public class BranchDAO extends BaseDAO<Branch> {

	public Integer addBranch(Connection conn, Branch branch) throws SQLException {
		return saveReturnPk(conn, "insert into tbl_library_branch (branchName, branchAddress) values (?,?)", 
				new Object[] { branch.getBranchName(), branch.getBranchAddress() });
	}

	public Boolean updateBranch(Connection conn, Branch branch) throws SQLException {
		return save(conn, "update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", 
				new Object[]{ branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId() }) > 0;
	}

	public Boolean deleteBranch(Connection conn, Integer branchId) throws SQLException {
		return save(conn, "delete from tbl_library_branch where branchId = ?", new Object[] { branchId }) > 0;
	}
	
	public List<Branch> readBranches(Connection conn) throws SQLException {
		return read(conn, "select * from tbl_library_branch", null);
	}
	
	public List<Branch> readBranchById(Connection conn, Integer branchId) throws SQLException {
		return read(conn, "select * from tbl_library_branch where branchId = ?", new Object[] { branchId });
	}

	@Override
	public List<Branch> extractData(Connection conn, ResultSet rs) throws SQLException {
		List<Branch> branches = new ArrayList<>();
		while(rs.next()){
			Branch b = new Branch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddress(rs.getString("branchAddress"));
			branches.add(b);
		}
		return branches;
	}

	@Override
	List<Branch> extractDataFirstLevel(Connection conn, ResultSet rs) throws SQLException {
		return extractData(conn, rs);
	}
	
}
