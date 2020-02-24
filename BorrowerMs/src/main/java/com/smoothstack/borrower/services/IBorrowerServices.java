package com.smoothstack.borrower.services;

import com.smoothstack.borrower.domain.CheckOutDetails;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;

public interface IBorrowerServices {

	CheckOutDetails checkOutBook(Integer branchId, Integer bookId, Integer cardNo) throws InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException;

	boolean checkInBook(Integer bookId, Integer cardNo) throws Exception;

}
