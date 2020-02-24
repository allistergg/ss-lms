package com.smoothstack.borrower.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book")
public class Book implements Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bookId")
  private Integer bookId;

  @Column(nullable = false, length = 20, name = "title")
  private String title;

  @Column(name = "pubId")
  private Integer pubid;

  /**
   * @return the bookId
   */
  public Integer getBookId() {
    return bookId;
  }

  /**
   * @param bookId the bookId to set
   */
  public void setBookId(Integer bookId) {
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
   * @return the pubid
   */
  public Integer getPubid() {
    return pubid;
  }

  /**
   * @param pubid the pubid to set
   */
  public void setPubid(Integer pubid) {
    this.pubid = pubid;
  }



  @Override
  public int hashCode() {
    return Objects.hash(bookId, pubid, title);
  }

  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Book other = (Book) obj;
    return Objects.equals(bookId, other.bookId) && Objects.equals(pubid, other.pubid)
        && Objects.equals(title, other.title);
  }

  @Override
  public String toString() {
    return "Book [bookId=" + bookId + ", title=" + title + ", pubid=" + pubid + "]";
  }

  /**
   * @param bookId
   * @param title
   * @param pubid
   */
  public Book(Integer bookId, String title, Integer pubid) {
    super();
    this.bookId = bookId;
    this.title = title;
    this.pubid = pubid;
  }

  /**
   * 
   */
  public Book() {
    super();
    // TODO Auto-generated constructor stub
  }



}
