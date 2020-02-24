package com.ss.lms.librarian.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.lms.librarian.entity.Publisher;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Long> {

}
