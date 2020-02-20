package com.smoothstack.lms.administrator.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.model.Borrower;

@Component
public class BorrowerDAO extends BaseDAO<Borrower> {

	@Autowired
	private LoanDAO ldao;

	public Integer addBorrower(Connection conn, Borrower borrower) throws SQLException {
		return saveReturnPk(conn, "insert into tbl_borrower (name, address, phone) values (?,?,?)", 
				new Object[] { borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone() });
	}

	public Boolean updateBorrower(Connection conn, Borrower borrower) throws SQLException {
		return save(conn, "update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?",
				new Object[]{ borrower.getBorrowerName(), borrower.getBorrowerAddress(),
						borrower.getBorrowerPhone(), borrower.getCardNo() }) > 0;
	}

	public Boolean deleteBorrower(Connection conn, Integer cardNo) throws SQLException {
		return save(conn, "delete from tbl_borrower where cardNo = ?", new Object[] { cardNo }) > 0;
	}
	
	public List<Borrower> readBorrowers(Connection conn) throws SQLException {
		return read(conn, "select * from tbl_borrower", null);
	}
	
	public List<Borrower> readBorrowerCardNo(Connection conn, Integer cardNo) throws SQLException {
		return read(conn, "select * from tbl_borrower where cardNo = ?", new Object[] { cardNo });
	}
	
	@Override
	public List<Borrower> extractData(Connection conn, ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setBorrowerAddress(rs.getString("address"));
			b.setBorrowerPhone(rs.getString("phone"));
			b.setLoans(ldao.readFirstLevel(conn, "select * from tbl_book_loans where cardNo = ?", new Object[] { rs.getInt("cardNo") }));
			borrowers.add(b);
		}
		return borrowers;
	}

	@Override
	List<Borrower> extractDataFirstLevel(Connection conn, ResultSet rs) throws SQLException {
		return null;
	}

}
