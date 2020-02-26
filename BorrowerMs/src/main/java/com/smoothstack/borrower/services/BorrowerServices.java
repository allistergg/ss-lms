package com.smoothstack.borrower.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.borrower.domain.Book;
import com.smoothstack.borrower.domain.BookCopies;
import com.smoothstack.borrower.domain.BookCopyID;
import com.smoothstack.borrower.domain.Borrower;
import com.smoothstack.borrower.domain.BorrowerID;
import com.smoothstack.borrower.domain.Branch;
import com.smoothstack.borrower.domain.CheckOutDetails;
import com.smoothstack.borrower.domain.Loans;
import com.smoothstack.borrower.exceptions.InvalidBookCountException;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;
import com.smoothstack.borrower.repositories.BookCopyRepository;
import com.smoothstack.borrower.repositories.BookRepository;
import com.smoothstack.borrower.repositories.BorrowerRepository;
import com.smoothstack.borrower.repositories.BranchRepository;
import com.smoothstack.borrower.repositories.LoansRepository;

@Service
public class BorrowerServices implements IBorrowerServices {

	private static final Logger log = LoggerFactory.getLogger(BorrowerServices.class);

	@Autowired
	private LoansRepository loanRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BorrowerRepository borrowerRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private BookCopyRepository bookCopyRepository;

	@Override
	@Transactional
	public CheckOutDetails checkOutBook(Integer bookId, Integer branchId, Integer cardNo)
			throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException,
			InvalidBookCountException {
		CheckOutDetails details = null;
		try {
			Optional<Book> b = bookRepository.findById(bookId);
			if (!b.isPresent()) {
				throw new InvalidBookIdException();
			}
			Optional<Branch> br = branchRepository.findById(branchId);
			if (!br.isPresent()) {
				throw new InvalidBranchIdException();
			}
			Optional<Borrower> bor = borrowerRepository.findById(cardNo);
			if (!bor.isPresent()) {
				throw new InvalidCardNumberException();
			}

			BookCopyID bci = new BookCopyID(bookId, branchId);
			Optional<BookCopies> bc = bookCopyRepository.findById(bci);
			Integer bookCount = 0;
			if (!bc.isPresent()) {
				throw new InvalidBookCountException();
			} else {
				bookCount = bc.get().getNoOfCopies();
				if (bookCount < 1) {
					throw new InvalidBookCountException();
				}
			}

			Timestamp dateOut = new java.sql.Timestamp(new Date().getTime());

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, +7);
			Timestamp dueDate = new java.sql.Timestamp(cal.getTime().getTime());
			Loans temploans = new Loans();

			temploans.setBorrowerid(new BorrowerID(bookId, branchId, cardNo));
			temploans.setDateDue(dueDate);
			temploans.setDateOut(dateOut);
			Loans newloans = loanRepository.save(temploans);
			if (newloans != null) {
				bookCount = bc.get().getNoOfCopies();
				bookCount--;
				log.info("bookCount is = " + bookCount);
				bc.get().setNoOfCopies(bookCount);
				bookCopyRepository.save(bc.get());
				Optional<Book> bk = bookRepository.findById(bookId);
				if (bk.isPresent()) {
					String bookTitle = bk.get().getTitle();
					details = new CheckOutDetails(bookTitle, temploans.getDateDue().toString());
				}
			}

		} catch (NoResultException e) {

			log.error("Please try again, as there was a database error. Unable to make changes." + e.getMessage());
			
			throw e;

		} catch (InvalidCardNumberException | InvalidBranchIdException | InvalidBookIdException e1) {

			log.error("Please try again, as there was invalid data. Unable to make changes, as a result."
					+ e1.getMessage());
		
			throw e1;

		} catch (InvalidBookCountException e2) {
			log.error("Please try again, as there were not enough books at that branch to fulfill your request."
					+ e2.getMessage());
			
			throw e2;
		}

		return details;

	}

	@Override
	@Transactional
	public boolean checkInBook(Integer bookId, Integer branchId, Integer cardNo)
			throws InvalidCardNumberException, InvalidBookIdException, InvalidBranchIdException {
		boolean status = false;
		try {

			Optional<Book> b = bookRepository.findById(bookId);
			if (!b.isPresent()) {
				throw new InvalidBookIdException();
			}

			Optional<Branch> br = branchRepository.findById(branchId);
			if (!br.isPresent()) {
				throw new InvalidBranchIdException();
			}
			Optional<Borrower> bor = borrowerRepository.findById(cardNo);
			if (!bor.isPresent()) {
				throw new InvalidCardNumberException();
			}

			BorrowerID bid = new BorrowerID(bookId, branchId, cardNo);
			Optional<Loans> temploans = loanRepository.findById(bid);
			if (temploans.isPresent()) {
				Timestamp dateIn = new java.sql.Timestamp(new Date().getTime());
				temploans.get().setDateIn(dateIn);

				Loans newloans = loanRepository.save(temploans.get());
				if (newloans != null) {
					BookCopyID bci = new BookCopyID(bookId, branchId);
					Optional<BookCopies> bc = bookCopyRepository.findById(bci);
					if (bc.isPresent()) {
						Integer bookCount = bc.get().getNoOfCopies();
						bookCount++;
						log.info("bookCount is = " + bookCount);
						bc.get().setNoOfCopies(bookCount);
						bookCopyRepository.save(bc.get());
					}
					status = true;

				}
			}

		} catch (NoResultException e) {

			log.error("Please try again, as there was a database error. Unable to make changes." + e.getMessage());

			throw e;

		} catch (InvalidCardNumberException | InvalidBookIdException | InvalidBranchIdException e1) {

			log.error("Please try again, as there was invalid data. Unable to make changes, as a result."
					+ e1.getMessage());
			throw e1;
		}

		return status;
	}

}