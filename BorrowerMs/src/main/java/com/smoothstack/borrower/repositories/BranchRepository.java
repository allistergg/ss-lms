package com.smoothstack.borrower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.domain.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
