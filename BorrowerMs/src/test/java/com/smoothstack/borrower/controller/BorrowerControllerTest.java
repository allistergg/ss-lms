/**
 * 
 */
package com.smoothstack.borrower.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import com.smoothstack.borrower.services.BorrowerServices;

/**
 * Unit test class for BorrowerController
 * @author vpns3
 *
 */
public class BorrowerControllerTest {

  
  @Test
  public void testCheckOutBook() throws Exception {
    
    BorrowerServices mockService = mock(BorrowerServices.class);
    BorrowerController bc = new BorrowerController(mockService);
    when(mockService.checkOutBook(anyInt(), anyInt(), anyInt())).thenReturn("some data");
    Response resp = bc.checkOutBook(1, 2, 1234);   
    assertTrue(resp.hasEntity());
    String status = (String) resp.getEntity();
    resp.close();
    assertEquals(status, "some data");
 
  }
  
  @Test
  public void testCheckOutBookWithException() throws Exception {
    
    BorrowerServices mockService = mock(BorrowerServices.class);
    BorrowerController bc = new BorrowerController(mockService);
    when(mockService.checkOutBook(anyInt(), anyInt(), anyInt())).thenThrow(new NullPointerException());
    Response resp = bc.checkOutBook(1, 2, 1234);   
    assertTrue(resp.hasEntity());
    String status = (String) resp.getEntity();
    resp.close();
    assertEquals(status, "Failed to check out a book; null");
 
  }
  
  @Test
  public void testCheckInBook() throws Exception {
    
    BorrowerServices mockService = mock(BorrowerServices.class);
    BorrowerController bc = new BorrowerController(mockService);
    when(mockService.checkInBook(anyInt(), anyInt())).thenReturn(true);
    Response resp = bc.checkInBook(2, 1234);   
    assertTrue(resp.hasEntity());
    String status = (String) resp.getEntity();
    resp.close();
    assertEquals(status, "Book has been checked in successfully.");
 
  }
  
  @Test
  public void testCheckInBookWithException() throws Exception {
    
    BorrowerServices mockService = mock(BorrowerServices.class);
    BorrowerController bc = new BorrowerController(mockService);
    when(mockService.checkInBook(anyInt(), anyInt())).thenThrow(new NullPointerException());
    Response resp = bc.checkInBook(2, 1234);   
    assertTrue(resp.hasEntity());
    String status = (String) resp.getEntity();
    resp.close();
    assertEquals(status, "Failed to check in the book.");
 
  }
}
