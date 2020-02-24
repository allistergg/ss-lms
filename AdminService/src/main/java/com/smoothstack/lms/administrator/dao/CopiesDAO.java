package com.smoothstack.lms.administrator.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.administrator.model.Copies;
import com.smoothstack.lms.administrator.model.CopiesIdentity;

@Repository
public interface CopiesDAO extends JpaRepository<Copies, CopiesIdentity> {

}
