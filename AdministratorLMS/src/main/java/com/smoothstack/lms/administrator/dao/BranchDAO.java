package com.smoothstack.lms.administrator.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.model.Branch;

@Component
public class BranchDAO extends BaseDAO<Branch> {

	public Integer addBranch(Branch branch) throws SQLException, ClassNotFoundException {
		return saveReturnPk("insert into tbl_library_branch (branchName, branchAddress) values (?,?)", 
				new Object[] { branch.getBranchName(), branch.getBranchAddress() });
	}

	public Boolean updateBranch(Branch branch) throws SQLException, ClassNotFoundException {
		return save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", 
				new Object[]{ branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId() }) > 0;
	}

	public Boolean deleteBranch(Integer branchId) throws SQLException, ClassNotFoundException {
		return save("delete from tbl_library_branch where branchId = ?", new Object[] { branchId }) > 0;
	}
	
	public List<Branch> readBranches() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_library_branch", null);
	}
	
	public Branch readBranchById(Integer branchId) throws SQLException, ClassNotFoundException {
		List<Branch> branches = read("select * from tbl_library_branch where branchId = ?", new Object[] { branchId });
		if (branches.size() == 0) {
			return null;
		}
		return branches.get(0);
	}

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException {
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
	List<Branch> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		return extractData(rs);
	}
	
}
