package com.smoothstack.borrower.services;

public interface IBorrowerServices {

	String checkOutBook(int branchId, int bookId, int cardNo) throws Exception;

	boolean checkInBook(int bookId, int cardNo) throws Exception;

}
