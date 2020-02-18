package com.smoothstack.borrower.domain;

public class CheckOutDetails {

	private String bookTitle;
	private String dueDate;

	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "CheckOutDetails [bookTitle=" + bookTitle + ", dueDate=" + dueDate + "]";
	}

	/**
	 * @param bookTitle
	 * @param dueDate
	 */
	public CheckOutDetails(String bookTitle, String dueDate) {
		super();
		this.bookTitle = bookTitle;
		this.dueDate = dueDate;
	}

	/**
	 * 
	 */
	public CheckOutDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}
