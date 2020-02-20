package com.smoothstack.borrower.services;

import com.smoothstack.borrower.domain.CheckOutDetails;

public interface IBorrowerServices {

	CheckOutDetails checkOutBook(Integer branchId, Integer bookId, Integer cardNo) throws Exception;

	boolean checkInBook(Integer bookId, Integer cardNo) throws Exception;

}
