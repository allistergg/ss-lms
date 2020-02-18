package com.smoothstack.lms.administrator.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Publisher implements Serializable {
	
	private static final long serialVersionUID = -159063459898671714L;
	private Integer publisherId;
	private String publisherName;
	private String publisherAddress;
	private String publisherPhone;
	private List<Book> books;
	
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublisherAddress() {
		return publisherAddress;
	}
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	public String getPublisherPhone() {
		return publisherPhone;
	}
	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public int hashCode() {
		return Objects.hash(books, publisherAddress, publisherId, publisherName, publisherPhone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publisher other = (Publisher) obj;
		return Objects.equals(books, other.books) && Objects.equals(publisherAddress, other.publisherAddress)
				&& Objects.equals(publisherId, other.publisherId) && Objects.equals(publisherName, other.publisherName)
				&& Objects.equals(publisherPhone, other.publisherPhone);
	}
	@Override
	public String toString() {
		return "Publisher [publisherId=" + publisherId + ", publisherName=" + publisherName + ", publisherAddress="
				+ publisherAddress + ", publisherPhone=" + publisherPhone + ", books=" + books + "]";
	}
	
	
	
	
	
}
