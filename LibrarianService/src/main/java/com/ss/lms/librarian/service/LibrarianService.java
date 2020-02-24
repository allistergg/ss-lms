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

<<<<<<< HEAD
	public void updateBranch(Branch branch) throws SQLException, ClassNotFoundException {
=======
	public void updateBranch(Branch branch) {
>>>>>>> 15c5d5a6e89bb354c8c748ec783b49363d63efeb
		brrepo.save(branch);
	}
	
	

}
