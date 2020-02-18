package com.smoothstack.lms.administrator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.smoothstack.lms.administrator.model.Publisher;



@SpringBootTest
class PublisherDAOTest {

	@Autowired
	PublisherDAO pdao;
	
	Publisher pub;
	
	@BeforeEach
	void setUp() throws Exception {
		
		List<Publisher> pubs = pdao.readPublishers();
		
		for (Publisher pub : pubs) {
			pdao.deletePublisher(pub.getPublisherId());
		}
		
		pub = new Publisher();

		pub.setPublisherName("Bantam");
		pub.setPublisherAddress("1 Wall Street");
		pub.setPublisherPhone("455-553-0343");
		pub.setBooks(new ArrayList<>());
		

	}
	
	@Test
	void testAddPublisher() throws ClassNotFoundException, SQLException {
		Integer key = pdao.addPublisher(pub);
		assertTrue(pdao.publisherExists(key));
		assertFalse(pdao.publisherExists(9999));
		pub.setPublisherId(key);
		Publisher pubFromDb = pdao.readPublisherById(key);
		System.out.println(pubFromDb);
		System.out.println(pub);
		assertEquals(pubFromDb, pub);
		assertFalse(pdao.deletePublisher(9999));
		pdao.deletePublisher(key);
		pubFromDb = pdao.readPublisherById(key);
		assertNull(pubFromDb);
	}

	@Test
	void testUpdatePublisher() throws Exception {
		Integer key = pdao.addPublisher(pub);
		pub.setPublisherId(9999);
		assertFalse(pdao.updatePublisher(pub));
		pub.setPublisherId(key);
		pub.setPublisherPhone("455-553-0000");
		pdao.updatePublisher(pub);
		Publisher pubFromDb = pdao.readPublisherById(pub.getPublisherId());
		System.out.println(pubFromDb);
		System.out.println(pub);
		assertEquals(pub, pubFromDb);
	}

}
