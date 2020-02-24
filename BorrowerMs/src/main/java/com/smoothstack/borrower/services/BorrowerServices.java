package com.smoothstack.borrower.services;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.borrower.daos.BorrowerDAO;
import com.smoothstack.borrower.domain.CheckOutDetails;
import com.smoothstack.borrower.domain.Loans;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;

@Service
@Transactional
public class BorrowerServices implements IBorrowerServices {

	private static final Logger log = LoggerFactory.getLogger(BorrowerServices.class);

	@Autowired
	private BorrowerDAO bdao;

	@Override
	public CheckOutDetails checkOutBook(Integer branchId, Integer bookId, Integer cardNo) throws SQLException,
			ClassNotFoundException, InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {
		CheckOutDetails details = null;

		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, +7);
			Date dueDate = cal.getTime();
			String bookTitle = bdao.checkOut(branchId, bookId, cardNo);

			details = new CheckOutDetails(bookTitle, dueDate.toString());

		} catch (NoResultException | ClassNotFoundException | SQLException e) {

			log.error("Please try again, as there was a database error. Unable to make changes." + e.getMessage());

			throw e;

		} catch (InvalidCardNumberException | InvalidBranchIdException | InvalidBookIdException e1) {

			log.error("Please try again, as there was invalid data. Unable to make changes, as a result."
					+ e1.getMessage());
			throw e1;
		}

		return details;

	}

	@Override
	public boolean checkInBook(Integer bookId, Integer cardNo)
			throws ClassNotFoundException, SQLException, InvalidCardNumberException, InvalidBookIdException {

		try {

			Loans loans = bdao.returnBook(bookId, cardNo);

			return loans != null;

		} catch (NoResultException | ClassNotFoundException | SQLException e) {

			log.error("Please try again, as there was a database error. Unable to make changes." + e.getMessage());
			throw e;
		} catch (InvalidCardNumberException | InvalidBookIdException e1) {

			log.error("Please try again, as there was invalid data. Unable to make changes, as a result."
					+ e1.getMessage());
			throw e1;

		}

	}
}