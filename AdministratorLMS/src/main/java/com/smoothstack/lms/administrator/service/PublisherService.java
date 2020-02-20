package com.smoothstack.lms.administrator.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.PublisherDAO;
import com.smoothstack.lms.administrator.model.Publisher;

@Service
public class PublisherService {
	
	@Autowired
	private ConnectionUtil connUtil;
	@Autowired
	private PublisherDAO pdao;
	
	// CREATE PUBLISHER
	public Integer savePublisher(Publisher publisher) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Integer publisherId = pdao.addPublisher(conn, publisher); // save new publisher.
			conn.commit();
			return publisherId;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// READ PUBLISHER BY ID
	public List<Publisher> getPublisherById(Integer publisherId) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return pdao.readPublisherById(conn, publisherId);
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// READ PUBLISHERS
	public List<Publisher> readPublishers() throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			return pdao.readPublishers(conn);
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// UPDATE PUBLISHER
	public Boolean updatePublisher(Publisher publisher) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = pdao.updatePublisher(conn, publisher);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}
	
	// DELETE PUBLISHER
	public Boolean deletePublisher(Integer publisherId) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {
			Boolean exists = pdao.deletePublisher(conn, publisherId);
			conn.commit();
			return exists;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}

}
