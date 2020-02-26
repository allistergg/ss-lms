package com.smoothstack.borrower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.domain.BorrowerID;
import com.smoothstack.borrower.domain.Loans;

@Repository
public interface LoansRepository extends JpaRepository<Loans, BorrowerID> {
	
}
