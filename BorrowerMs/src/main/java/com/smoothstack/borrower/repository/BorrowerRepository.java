package com.smoothstack.borrower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.domain.Loans;

@Repository
public interface BorrowerRepository extends JpaRepository<Loans, Integer> {

}
