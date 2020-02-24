/**
 * 
 */
package com.smoothstack.borrower.daos;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;
import com.smoothstack.borrower.domain.Book;
import com.smoothstack.borrower.domain.Borrower;
import com.smoothstack.borrower.domain.Branch;
import com.smoothstack.borrower.domain.Loans;
import com.smoothstack.borrower.exceptions.InvalidBookIdException;
import com.smoothstack.borrower.exceptions.InvalidBranchIdException;
import com.smoothstack.borrower.exceptions.InvalidCardNumberException;

/**
 * Unit test class for BorrowerDAO
 * @author Pramod Varanasi
 *
 */
public class BorrowerDAOTest {

  private EntityManager mockEm;
  private BorrowerDAO borrowerDao;
  
  @Before
  public void setup() {
    
    mockEm = mock(EntityManager.class);
    this.borrowerDao = new BorrowerDAO(mockEm);
  }
 // @Test
  public void testCheckout() throws NoResultException, ClassNotFoundException, SQLException, InvalidCardNumberException, InvalidBranchIdException, InvalidBookIdException {
    
    Query mockQuery = mock(Query.class);
    when(mockEm.createNativeQuery(anyString())).thenReturn(mockQuery);
    when(mockQuery.executeUpdate()).thenReturn(1);
    
    when(mockEm.createQuery(anyString())).thenReturn(mockQuery);
    Query mockQuery1 = mock(Query.class);
    when(mockQuery.setParameter(anyInt(),  anyInt())).thenReturn(mockQuery1);
    when(mockQuery1.getSingleResult()).thenReturn(new Object());

    when(mockEm.createQuery(anyString())).thenReturn(mockQuery);
    Query mockQuery2 = mock(Query.class);
    when(mockQuery.setParameter(anyInt(),  anyInt())).thenReturn(mockQuery2);
    when(mockQuery2.getSingleResult()).thenReturn(new Object());
    
    when(mockEm.createQuery(anyString())).thenReturn(mockQuery);
    Query mockQuery3 = mock(Query.class);
    when(mockQuery.setParameter(anyInt(),  anyInt())).thenReturn(mockQuery3);
    when(mockQuery3.getSingleResult()).thenReturn(new Object());
    
  /*  when(mockEm.createQuery(anyString())).thenReturn(mockQuery);
    Query mockQuery4 = mock(Query.class);
    when(mockQuery.setParameter(anyInt(),  anyInt())).thenReturn(mockQuery4);
    when(mockQuery4.getSingleResult()).thenReturn(any());*/

  //  when(mockEm.createQuery(anyString()).executeUpdate()).thenReturn(1);
       
 //   when(this.mockEm.createQuery(anyString())).thenReturn(new Loans());
    String resp = borrowerDao.checkOut(12, 34,  567);
    
    
  }
}
