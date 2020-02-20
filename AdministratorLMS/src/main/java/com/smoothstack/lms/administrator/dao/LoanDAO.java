package com.smoothstack.lms.administrator.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.model.Loan;

@Component
public class LoanDAO extends BaseDAO<Loan> {
	
	@Autowired
	private BookDAO bdao;
	@Autowired
	private BranchDAO brdao;

	@Override
	List<Loan> extractData(Connection conn, ResultSet rs) throws SQLException {
		return null;
	}

	@Override
	List<Loan> extractDataFirstLevel(Connection conn, ResultSet rs) throws SQLException {
		List<Loan> loans = new ArrayList<>();
		while(rs.next()){
			Loan l = new Loan();
			l.setBook(bdao.readFirstLevel(conn, "select * from tbl_book where bookId = ?", new Object[]{ rs.getInt("bookId") }).get(0));
			l.setBranch(brdao.readFirstLevel(conn, "select * from tbl_library_branch where branchId = ?", new Object[]{ rs.getInt("branchId") }).get(0));
			l.setDateOut(rs.getDate("dateOut").toLocalDate());
			l.setDateDue(rs.getDate("dueDate").toLocalDate());
			if (rs.getDate("dateIn") != null) {
				l.setDateIn(rs.getDate("dateIn").toLocalDate());
			}
			loans.add(l);
		}
		return loans;
	}

}
