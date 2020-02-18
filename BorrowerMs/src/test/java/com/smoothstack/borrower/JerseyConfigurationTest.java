package com.smoothstack.borrower;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class JerseyConfigurationTest {

  
  @Test
  public void testJerseyConfig() {
    JerseyConfiguration config = new JerseyConfiguration();
    assertNotNull(config);
    config.setUp();
  }
}
