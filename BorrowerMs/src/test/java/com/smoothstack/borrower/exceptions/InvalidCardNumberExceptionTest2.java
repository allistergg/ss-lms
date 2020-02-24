/**
 * 
 */
package com.smoothstack.borrower.exceptions;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test class for InvalidCardNumberException
 * 
 * @author Pramod Varanasi
 *
 */
public class InvalidCardNumberExceptionTest2 {

  @Test
  public void testException() {
    InvalidCardNumberException e = new InvalidCardNumberException();
    e = new InvalidCardNumberException("some message");
    assertEquals(e.getMessage(), "some message");
    e = new InvalidCardNumberException("some message", new Throwable());
    assertEquals(e.getMessage(), "some message");
    Throwable t = new NullPointerException();
    e = new InvalidCardNumberException(t);
    assertEquals(e.getCause(), t);
  }
}
