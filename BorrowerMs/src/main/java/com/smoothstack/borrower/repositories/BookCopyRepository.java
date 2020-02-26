package com.smoothstack.borrower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.domain.BookCopies;
import com.smoothstack.borrower.domain.BookCopyID;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopies, BookCopyID> {

}
