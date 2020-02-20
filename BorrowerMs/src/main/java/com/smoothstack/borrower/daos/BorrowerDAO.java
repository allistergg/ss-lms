package com.smoothstack.borrower.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.smoothstack.borrower.exceptions.InvalidCardNumberException;
import com.smoothstack.borrower.services.ConnectionUtil;

@Component
public class BorrowerDAO {
	private static final Logger log = LoggerFactory.getLogger(BorrowerDAO.class);

	public boolean checkOut(Integer branchId, Integer bookId, Integer cardNo)
			throws SQLException, ClassNotFoundException, InvalidCardNumberException {

		Date dateOut = new java.sql.Timestamp(new Date().getTime());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +7);
		Date dueDate = new java.sql.Timestamp(cal.getTime().getTime());
		if (!isCardNumberValid(cardNo)) {
			throw new InvalidCardNumberException();
		}
		Connection connection = ConnectionUtil.getConnection();
		setForeignKeyChecks(0);

		String sql = "INSERT INTO tbl_book_loans(bookId, branchId, cardNo, dateOut, dueDate) VALUES('" + bookId + "', '"
				+ branchId + "', '" + cardNo + "', '" + dateOut + "', '" + dueDate + "')";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.execute();

		setForeignKeyChecks(1);

		return true;

	}

	public boolean returnBook(Integer bookId, Integer cardNo)
			throws ClassNotFoundException, SQLException, InvalidCardNumberException {

		Date dateIn = new java.sql.Timestamp(new Date().getTime());
		if (!isCardNumberValid(cardNo)) {
			throw new InvalidCardNumberException();
		}
		Connection connection = ConnectionUtil.getConnection();

		String sql = "UPDATE tbl_book_loans SET dateIn ='" + dateIn + "' where cardNo = " + cardNo + " and bookId = "
				+ bookId;
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.execute();
		return true;

	}

	private void setForeignKeyChecks(int val) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SET FOREIGN_KEY_CHECKS= " + val;
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.execute();
	}

	public String getBookTitle(Integer bookId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT title from tbl_book where bookId =" + bookId;
		PreparedStatement statement = null;
		String title = "";

		statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			title = rs.getString("title");

		}

		return title;
	}

	public boolean isCardNumberValid(Integer cardNo) throws SQLException, ClassNotFoundException {
		Connection connection = ConnectionUtil.getConnection();
		String sql = "SELECT * from tbl_borrower where cardNo = " + cardNo;
		PreparedStatement statement = null;
		String title = "";

		statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		return rs.next() == false;

	}
}