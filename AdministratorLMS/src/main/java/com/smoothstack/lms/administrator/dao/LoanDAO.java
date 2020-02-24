package com.smoothstack.lms.administrator.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.administrator.model.Loan;
import com.smoothstack.lms.administrator.model.LoansIdentity;

@Repository
public interface LoanDAO extends JpaRepository<Loan, LoansIdentity> {

}
