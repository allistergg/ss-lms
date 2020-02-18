package com.smoothstack.borrower.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smoothstack.borrower.domain.CheckOutDetails;

@Service
public class BorrowerServices implements IBorrowerServices {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String checkOutBook(int branchId, int bookId, int cardNo) throws Exception {
		String status = "";
		try {

			Date dateOut = new java.sql.Timestamp(new Date().getTime());

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, +7);
			Date dueDate = cal.getTime();
			Date dueDateTimeStamp = new java.sql.Timestamp(dueDate.getTime());

			String sql = "SET FOREIGN_KEY_CHECKS=0";

			jdbcTemplate.execute(sql);

			sql = "INSERT INTO tbl_book_loans(bookId, branchId, cardNo, dateOut, dueDate) VALUES('" + bookId + "', '"
					+ branchId + "', '" + cardNo + "', '" + dateOut + "', '" + dueDateTimeStamp + "')";
			jdbcTemplate.execute(sql);

			sql = "SET FOREIGN_KEY_CHECKS=1";
			jdbcTemplate.execute(sql);

			
			sql = "SELECT title from tbl_book where bookId = ?";
			
			String bookTitle = (String) jdbcTemplate.queryForObject(sql, new Object[] { bookId }, String.class);

			ObjectMapper mapper = new ObjectMapper();
			CheckOutDetails details = new CheckOutDetails(bookTitle, dueDate.toString());
			status = mapper.writeValueAsString(details);
		} catch (Exception e) {
			throw e;
		}
		return status;

	}

	@Override
	public boolean checkInBook(int bookId, int cardNo) throws Exception {

		try {
			Date dateIn = new java.sql.Timestamp(new Date().getTime());

			String sql = "UPDATE tbl_book_loans SET dateIn ='" + dateIn + "' where cardNo = '" + cardNo
					+ "' and bookId = '" + bookId + "'";
			int rows = jdbcTemplate.update(sql);
			return rows > 0;

		} catch (Exception e) {
			throw e;
		}

	}

}
