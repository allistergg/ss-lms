package com.ss.lms.librarian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.smoothstack.lms.administrator.model")
public class LibrarianServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarianServiceApplication.class, args);
	}

}
