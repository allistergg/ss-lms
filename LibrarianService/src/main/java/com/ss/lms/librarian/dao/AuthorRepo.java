package com.ss.lms.librarian.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.administrator.model.Author;





@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

}
