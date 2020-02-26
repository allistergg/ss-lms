package com.ss.lms.librarian.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.model.Branch;
import com.ss.lms.librarian.dao.BranchRepo;


@Service
public class LibrarianService {

	@Autowired
	BranchRepo brrepo;

	public List<Branch> findAllBranches(){
		
		return brrepo.findAll();

	}
	
	public Optional<Branch> findBranchById(Integer id) {
		
		return brrepo.findByBranchId(id);
	}

	@Transactional
	public void updateBranch(Branch branch) {
		brrepo.save(branch);
	}
	
	

}
