/**
 * 
 */
package com.smoothstack.borrower.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * 
 * Unit test class for Book
 * 
 * @author Pramod Varanasi
 *
 */
public class BookTest {

  @Test
  public void testBook() {

    Book b = new Book();
    b.setBookId(123);
    assertTrue(b.getBookId() == 123);
    b.setPubid(345);
    assertTrue(b.getPubid() == 345);
    b.setTitle("Harry Potter");
    assertEquals(b.getTitle(), "Harry Potter");
    assertTrue(b.toString().contains("Harry Potter"));
    Book b1 = new Book(123, "Harry Potter", 345);
    Book b2 = new Book();
    assertTrue(b.hashCode() != b2.hashCode());
    assertTrue(b.hashCode() == b1.hashCode());

    assertFalse(b.equals(null));
    assertFalse(b.equals(b2));
    assertTrue(b.equals(b));
    assertTrue(b.equals(b1));
  }
}
