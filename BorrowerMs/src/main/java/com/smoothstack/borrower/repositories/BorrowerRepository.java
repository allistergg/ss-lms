package com.smoothstack.borrower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.domain.Borrower;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {

}
