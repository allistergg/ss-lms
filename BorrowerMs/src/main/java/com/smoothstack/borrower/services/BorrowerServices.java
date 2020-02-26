package com.smoothstack.borrower.services;

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
	public CheckOutDetails checkOutBook(Loans loans) throws InvalidCardNumberException, InvalidBranchIdException,
			InvalidBookIdException, InvalidBookCountException {
		CheckOutDetails details = null;
		Integer bookId = -1;
		Integer branchId = -1;
		Integer cardNo = -1;
		try {
			if (loans != null) {
				bookId = loans.getBook().getBookId();
				Optional<Book> b = bookRepository.findById(bookId);
				if (!b.isPresent()) {
					throw new InvalidBookIdException();
				}
				branchId = loans.getBranch().getBranchId();
				Optional<Branch> br = branchRepository.findById(branchId);
				if (!br.isPresent()) {
					throw new InvalidBranchIdException();
				}
				cardNo = loans.getBorrower().getCardNo();
				Optional<Borrower> bor = borrowerRepository.findById(cardNo);
				if (!bor.isPresent()) {
					throw new InvalidCardNumberException();
				}
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
			Loans temploans = new Loans();
			temploans.setBorrowerid(new BorrowerID(bookId, branchId, cardNo));
			temploans.setDateDue(loans.getDateDue());
			temploans.setDateOut(loans.getDateOut());
			Loans newloans = loanRepository.save(temploans);
			if (newloans != null) {
				bookCount = bc.get().getNoOfCopies();
				bookCount--;
				log.info("bookCount is = " + bookCount);
				bc.get().setNoOfCopies(bookCount);
				bookCopyRepository.save(bc.get());
				Optional<Book> b = bookRepository.findById(bookId);
				if (b.isPresent()) {
					String bookTitle = b.get().getTitle();
					details = new CheckOutDetails(bookTitle, temploans.getDateDue().toString());
				}
			}

		} catch (NoResultException e) {

			log.error("Please try again, as there was a database error. Unable to make changes." + e.getMessage());
			e.printStackTrace();
			throw e;

		} catch (InvalidCardNumberException | InvalidBranchIdException | InvalidBookIdException e1) {

			log.error("Please try again, as there was invalid data. Unable to make changes, as a result."
					+ e1.getMessage());
			e1.printStackTrace();
			throw e1;

		} catch (InvalidBookCountException e2) {
			log.error("Please try again, as there were not enough books at that branch to fulfill your request."
					+ e2.getMessage());
			e2.printStackTrace();
			throw e2;
		}

		return details;

	}

	@Override
	@Transactional
	public boolean checkInBook(Loans loans)
			throws InvalidCardNumberException, InvalidBookIdException, InvalidBranchIdException {
		boolean status = false;
		Integer bookId = -1;
		Integer branchId = -1;
		Integer cardNo = -1;
		try {
			if (loans != null) {
				bookId = loans.getBook().getBookId();
				Optional<Book> b = bookRepository.findById(bookId);
				if (!b.isPresent()) {
					throw new InvalidBookIdException();
				}

				branchId = loans.getBranch().getBranchId();
				Optional<Branch> br = branchRepository.findById(branchId);
				if (!br.isPresent()) {
					throw new InvalidBranchIdException();
				}
				cardNo = loans.getBorrower().getCardNo();
				Optional<Borrower> bor = borrowerRepository.findById(cardNo);
				if (!bor.isPresent()) {
					throw new InvalidCardNumberException();
				}
			}
			BorrowerID bid = new BorrowerID(bookId, branchId, cardNo);
			Optional<Loans> temploans = loanRepository.findById(bid);
			if (temploans.isPresent()) {
				temploans.get().setDateIn(loans.getDateIn());

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