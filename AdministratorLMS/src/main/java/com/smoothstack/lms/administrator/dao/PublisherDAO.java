package com.smoothstack.lms.administrator.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smoothstack.lms.administrator.model.Publisher;

@Component
public class PublisherDAO extends BaseDAO<Publisher> {
	
	@Autowired
	private BookDAO bdao;
	
	public Integer addPublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		return saveReturnPk("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)", 
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone() });
	}

	public Boolean updatePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		return save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ?" + 
	" where publisherId = ?", new Object[]{ publisher.getPublisherName(), publisher.getPublisherAddress(),
			publisher.getPublisherPhone(), publisher.getPublisherId() }) > 0;
	}

	public Boolean deletePublisher(Integer publisherId) throws SQLException, ClassNotFoundException {
		return save("delete from tbl_publisher where publisherId = ?", new Object[] { publisherId }) > 0;
	}
	
	public List<Publisher> readPublishers() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_publisher", null);
	}
	
	public Publisher readPublisherById(Integer publisherId) throws SQLException, ClassNotFoundException {
		List<Publisher> publishers = read("select * from tbl_publisher where publisherId = ?", new Object[] { publisherId });
		if (publishers.size() == 0) {
			return null;
		}
		return publishers.get(0);
	}
	
	public Boolean publisherExists(Integer publisherId) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_publisher where publisherId = ?", new Object[] { publisherId }).size() > 0;
	}
	
	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			p.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId IN "
					+ "(select bookId from tbl_book where pubId = ?)", new Object[] { p.getPublisherId() }));
			publishers.add(p);
		}
		return publishers;
	}

	@Override
	List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(p);
		}
		return publishers;
	}

}
