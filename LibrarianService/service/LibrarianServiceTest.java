package com.ss.lms.librarian.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ss.lms.librarian.dao.BranchRepo;
import com.ss.lms.librarian.entity.Branch;

class LibrarianServiceTest {

	@Mock
	private BranchRepo repo;
	
	@InjectMocks
	private LibrarianService service;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Branch branch = new Branch();
		branch.setBranchId(1);
		branch.setBranchName("Mornington");
		branch.setBranchAddress("1 Penny Lane");
		List<Branch> branches = Collections.singletonList(branch);
		doReturn(Optional.of(branch)).when(repo).findByBranchId(1);
		doReturn(branches).when(repo).findAll();
		
	}

	@Test
	void testFindAllBranches() {
		
		Branch branch = new Branch();
		branch.setBranchId(1);
		branch.setBranchName("Mornington");
		branch.setBranchAddress("1 Penny Lane");
		List<Branch> branches = Collections.singletonList(branch);
		assertEquals(branches, service.findAllBranches());
	}

	@Test
	void testFindBranchById() {
		Branch branch = new Branch();
		branch.setBranchId(1);
		branch.setBranchName("Mornington");
		branch.setBranchAddress("1 Penny Lane");
		assertEquals(Optional.of(branch), service.findBranchById(1));
	}

	@Test
	void testUpdateBranch() {
		Branch branch = new Branch();
		branch.setBranchId(1);
		branch.setBranchName("Mornington");
		branch.setBranchAddress("1 Penny Lane");
		service.updateBranch(branch);
		verify(repo, times(1)).save(branch);
	}

}
