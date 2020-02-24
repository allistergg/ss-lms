package com.smoothstack.borrower.daos;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.smoothstack.borrower.domain.Book;
import com.smoothstack.borrower.domain.Branch;
import com.smoothstack.borrower.domain.Loans;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;

@Component
@Transactional
public class BorrowerDAO {
	private static final Logger log = LoggerFactory.getLogger(BorrowerDAO.class);
	private EntityManager entityManager;

	public BorrowerDAO(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	public String checkOut(Integer branchId, Integer bookId, Integer cardNo)

			throws SQLException, ClassNotFoundException, NoResultException, InvalidCardNumberException, InvalidBranchIdException,
			InvalidBookIdException {

		Timestamp dateOut = new java.sql.Timestamp(new Date().getTime());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +7);
		Timestamp dueDate = new java.sql.Timestamp(cal.getTime().getTime());
		String bookTitle = "";
		try {
			setForeignKeyChecks(0);
			if (!isCardNumberValid(cardNo)) {
				throw new InvalidCardNumberException();
			}

			if (!isBookIdValid(bookId)) {
				throw new InvalidBookIdException();
			}
			if (!isBranchIdValid(branchId)) {
				throw new InvalidBranchIdException();
			}

			String hsql = " INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES(" + bookId
					+ ", " + branchId + ", " + cardNo + ", '" + dateOut + "', '" + dueDate + "')";
			log.info(hsql);

			Query query = entityManager.createNativeQuery(hsql);
			entityManager.flush();
			int result = query.executeUpdate();
			entityManager.clear();

			log.info("results = " + result);

			setForeignKeyChecks(1);

			if (result >= 1) {
				bookTitle = getBookTitle(bookId);
			}

		} catch (NoResultException nre) {
			log.error("Failed to check out due to " + nre.getMessage());
			throw nre;
		}
		return bookTitle;
	}

	public Loans returnBook(Integer bookId, Integer cardNo)
			throws NoResultException, InvalidCardNumberException, InvalidBookIdException, ClassNotFoundException, SQLException {

		Loans loans = null;
		Timestamp dateIn = new java.sql.Timestamp(new Date().getTime());
		
		
		if (!isCardNumberValid(cardNo)) {
			throw new InvalidCardNumberException();
		}
		if (!isBookIdValid(bookId)) {
			throw new InvalidBookIdException();
		}
		try {
		
		String hsql = "UPDATE Loans SET dateIn='" + dateIn + "' where bookId =" + bookId + " and cardNo = " + cardNo;
		//log.info(hsql);
		Query query = entityManager.createQuery(hsql);
		entityManager.flush();
		Integer result = query.executeUpdate();
		entityManager.clear();

		loans = (Loans) entityManager.createQuery("SELECT l from Loans l where l.bookId = ?1 and l.cardNo = ?2")
				.setParameter(1, bookId).setParameter(2, cardNo).getSingleResult();

		//log.info(loans.toString());
		return loans;
		} catch(NoResultException nre){
			log.error("Failed to check in due to " + nre.getMessage());
			throw nre;
		}
	}

	public String getBookTitle(Integer bookId) throws ClassNotFoundException, SQLException {
		Book book = (Book) entityManager.createQuery("SELECT b from Book b where b.bookId = ?1").setParameter(1, bookId)
				.getSingleResult();

		return book != null ? book.getTitle() : "";
	}

	public boolean isCardNumberValid(Integer cardNo) throws SQLException, ClassNotFoundException {

		Loans loans = (Loans) entityManager.createQuery("SELECT l from Loans l where l.cardNo = ?1")
				.setParameter(1, cardNo).getSingleResult();

		return loans != null;

	}

	public boolean isBookIdValid(Integer bookId) throws SQLException, ClassNotFoundException {

		Book book = (Book) entityManager.createQuery("SELECT b from Book b where b.bookId = ?1").setParameter(1, bookId)
				.getSingleResult();

		return book != null;
	}

	public boolean isBranchIdValid(Integer branchId) throws SQLException, ClassNotFoundException {

		Branch branch = (Branch) entityManager.createQuery("SELECT br from Branch br where br.branchId = ?1")
				.setParameter(1, branchId).getSingleResult();

		return branch != null;
	}

	private void setForeignKeyChecks(int val) throws ClassNotFoundException, SQLException {

		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS= " + val).executeUpdate();

	}

}
