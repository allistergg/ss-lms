package com.smoothstack.lms.administrator.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.smoothstack.lms.administrator.model.Publisher;
import com.smoothstack.lms.administrator.service.ConnectionUtil;



@SpringBootTest
class PublisherDAOTest {
	
	@Autowired
	private ConnectionUtil connUtil;
	@Autowired
	private PublisherDAO pdao;
	
	@Test
	void testAddPublisher() throws ClassNotFoundException, SQLException {
		Connection conn = connUtil.getConnection();
		Publisher pub = new Publisher();
		pub.setPublisherName("Bantam");
		pub.setPublisherAddress("1 Wall Street");
		pub.setPublisherPhone("455-553-0343");
		pub.setBooks(new ArrayList<>());
		Integer key = pdao.addPublisher(conn, pub);
		pub.setPublisherId(key);
		Publisher pubFromDb = pdao.readPublisherById(conn, key).get(0);
		System.out.println(pubFromDb);
		System.out.println(pub);
		assertEquals(pubFromDb, pub);
		conn.rollback();
		conn.close();
	}

}
