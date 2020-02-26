package com.smoothstack.borrower.services;

import com.smoothstack.borrower.domain.CheckOutDetails;
import com.smoothstack.borrower.domain.Loans;
import com.smoothstack.borrower.exceptions.InvalidBookCountException;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;

public interface IBorrowerServices {

	CheckOutDetails checkOutBook(Loans loans) throws InvalidCardNumberException, InvalidBranchIdException,
			InvalidBookIdException, InvalidBookCountException;

	boolean checkInBook(Loans loans)
			throws InvalidCardNumberException, InvalidBookIdException, InvalidBranchIdException;

}
