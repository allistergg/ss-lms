package com.ss.lms.librarian.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.lms.librarian.dao.BranchRepo;
import com.ss.lms.librarian.entity.Branch;

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

	public void updateBranch(Branch branch) {
		brrepo.save(branch);
	}
	
	

}
