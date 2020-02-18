
package com.smoothstack.borrower;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.smoothstack.borrower.controller.BorrowerController;

@Configuration
@ApplicationPath("rest")
public class JerseyConfiguration extends ResourceConfig {
   public JerseyConfiguration() {
       
   }
   
   @PostConstruct
   public void setUp() {
       register(BorrowerController.class);
   }
}