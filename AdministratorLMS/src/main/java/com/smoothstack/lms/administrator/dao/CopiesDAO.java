package com.smoothstack.lms.administrator.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.model.Copies;

@Component
public class CopiesDAO extends BaseDAO<Copies>{
	
	@Autowired
	private BranchDAO brdao;

	@Override
	List<Copies> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		return null;
	}

	@Override
	List<Copies> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Copies> copies = new ArrayList<>();
		while(rs.next()){
			Copies c = new Copies();
			c.setBranch(brdao.readFirstLevel("select * from tbl_library_branch where branchId = ?", new Object[] { rs.getInt("branchId") }).get(0));
			c.setNoOfCopies(rs.getInt("noOfCopies"));
			copies.add(c);
		}
		return copies;
	}

}
