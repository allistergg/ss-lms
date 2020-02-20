package com.smoothstack.borrower.domain;

import java.sql.Date;

/**
 * @author vpns3
 *
 */
public class Loans {

	private int bookId;
	private String title;
	private Date dateOut;
	private Date dueDate;

	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the dateOut
	 */
	public Date getDateOut() {
		return dateOut;
	}

	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @param bookId
	 * @param title
	 * @param dateOut
	 * @param dueDate
	 */
	public Loans(int bookId, String title, Date dateOut, Date dueDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Loans [bookId=" + bookId + ", title=" + title + ", dateOut=" + dateOut + ", dueDate=" + dueDate + "]";
	}

}
