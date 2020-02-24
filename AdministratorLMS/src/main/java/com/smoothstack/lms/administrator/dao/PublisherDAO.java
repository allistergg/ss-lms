package com.smoothstack.lms.administrator.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.administrator.model.Publisher;

@Repository
public interface PublisherDAO extends JpaRepository<Publisher, Integer> {
	
}
