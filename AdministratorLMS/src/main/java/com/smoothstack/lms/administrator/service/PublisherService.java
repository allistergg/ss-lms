package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.dao.PublisherDAO;
import com.smoothstack.lms.administrator.model.Publisher;

@Component
public class PublisherService {
	
	@Autowired
	private Connection conn;
	@Autowired
	private PublisherDAO pdao;
	
	// CREATE PUBLISHER
	public Integer savePublisher(Publisher publisher) throws SQLException {
		try {
			Integer publisherId = pdao.addPublisher(publisher); // save new publisher.
			conn.commit();
			return publisherId;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with add publisher.");
			conn.rollback();
		} 
		return null;
	}
	
	// READ PUBLISHERS
	public List<Publisher> readPublishers() throws SQLException{
		try {
			return pdao.readPublishers();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read publishers.");
		} 
		return null;
	}
	
	// UPDATE PUBLISHER
	public Boolean updatePublisher(Publisher publisher) throws SQLException{
		try {
			Boolean exists = pdao.updatePublisher(publisher);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with update publisher.");
			conn.rollback();
		} 
		return null;
	}
	
	// DELETE PUBLISHER
	public Boolean deletePublisher(Integer publisherId) throws SQLException{
		try {
			Boolean exists = pdao.deletePublisher(publisherId);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with delete Publisher");
			conn.rollback();
		} 
		return null;
	}

	public Publisher getPublisherById(Integer publisherId) throws SQLException {
		try {
			return pdao.readPublisherById(publisherId);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Something failed with read publisher by id.");
		} 
		return null;
	}
	

}
