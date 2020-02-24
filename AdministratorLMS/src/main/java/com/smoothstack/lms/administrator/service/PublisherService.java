package com.smoothstack.lms.administrator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.PublisherDAO;
import com.smoothstack.lms.administrator.model.Publisher;

@Service
public class PublisherService {
	
	@Autowired
	private PublisherDAO pdao;
	
	// CREATE PUBLISHER
	public Publisher savePublisher(Publisher publisher) {
		return pdao.save(publisher);
	}
	
	// READ PUBLISHER BY ID
	public Result<Publisher> getPublisherById(Integer publisherId) {
		Result<Publisher> rs = new Result<Publisher>();
		if (pdao.existsById(publisherId)) {
			rs.setResult(pdao.findById(publisherId).get());
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}
	
	// READ PUBLISHERS
	public List<Publisher> readPublishers() {
		return pdao.findAll();
	}
	
	// UPDATE PUBLISHER
	public Result<Void> updatePublisher(Publisher publisher) {
		Result<Void> rs = new Result<Void>();
		if (pdao.existsById(publisher.getPublisherId())) {
			pdao.save(publisher);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}
	
	// DELETE PUBLISHER
	public Result<Void> deletePublisher(Integer publisherId) {
		Result<Void> rs = new Result<Void>();
		if (pdao.existsById(publisherId)) {
			pdao.deleteById(publisherId);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}

}
