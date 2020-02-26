package com.smoothstack.borrower.services;

import com.smoothstack.borrower.domain.CheckOutDetails;
import com.smoothstack.borrower.exceptions.InvalidBookCountException;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;

public interface IBorrowerServices {

	CheckOutDetails checkOutBook(Integer bookId, Integer branchId, Integer cardNo) throws InvalidCardNumberException, InvalidBranchIdException,
			InvalidBookIdException, InvalidBookCountException;

	boolean checkInBook(Integer bookId, Integer branchId, Integer cardNo)
			throws InvalidCardNumberException, InvalidBookIdException, InvalidBranchIdException;

}
