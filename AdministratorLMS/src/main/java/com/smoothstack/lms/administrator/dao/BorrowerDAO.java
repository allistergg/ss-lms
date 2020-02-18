package com.smoothstack.lms.administrator.dao;

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

	public Integer addBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		return saveReturnPk("insert into tbl_borrower (name, address, phone) values (?,?,?)", 
				new Object[] { borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone() });
	}

	public Boolean updateBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		return save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?",
				new Object[]{ borrower.getBorrowerName(), borrower.getBorrowerAddress(),
						borrower.getBorrowerPhone(), borrower.getCardNo() }) > 0;
	}

	public Boolean deleteBorrower(Integer cardNo) throws SQLException, ClassNotFoundException {
		return save("delete from tbl_borrower where cardNo = ?", new Object[] { cardNo }) > 0;
	}
	
	public List<Borrower> readBorrowers() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_borrower", null);
	}
	
	public Borrower readBorrowerCardNo(Integer cardNo) throws SQLException, ClassNotFoundException {
		List<Borrower> borrowers = read("select * from tbl_borrower where cardNo = ?", new Object[] { cardNo });
		if (borrowers.size() == 0) {
			return null;
		}
		return borrowers.get(0);
	}
	
	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Borrower> borrowers = new ArrayList<>();
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setBorrowerAddress(rs.getString("address"));
			b.setBorrowerPhone(rs.getString("phone"));
			b.setLoans(ldao.readFirstLevel("select * from tbl_book_loans where cardNo = ?", new Object[] { rs.getInt("cardNo") }));
			borrowers.add(b);
		}
		return borrowers;
	}

	@Override
	List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		return null;
	}

}
