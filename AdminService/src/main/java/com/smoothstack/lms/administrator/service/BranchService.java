package com.smoothstack.lms.administrator.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.BranchDAO;
import com.smoothstack.lms.administrator.model.Branch;

@Service
public class BranchService {
	
	@Autowired
	private BranchDAO brdao;
	
	// CREATE BRANCH
	public Branch saveBranch(Branch branch) {
		return brdao.save(branch);
	}
	
	// READ BRANCH BY ID
	public Result<Branch> getBranchById(Integer branchId) {
		Result<Branch> rs = new Result<Branch>();
		if (brdao.existsById(branchId)) {
			rs.setResult(brdao.findById(branchId).get());
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}	
	
	// READ BRANCHES
	public List<Branch> readBranches() {
		return brdao.findAll();
	}
	
	// UPDATE BRANCH
	public Result<Void> updateBranch(Branch branch) {
		Result<Void> rs = new Result<Void>();
		if (brdao.existsById(branch.getBranchId())) {
			brdao.save(branch);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}
	
	// DELETE BRANCH
	public Result<Void> deleteBranch(Integer branchId) {
		Result<Void> rs = new Result<Void>();
		if (brdao.existsById(branchId)) {
			brdao.deleteById(branchId);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}

}
