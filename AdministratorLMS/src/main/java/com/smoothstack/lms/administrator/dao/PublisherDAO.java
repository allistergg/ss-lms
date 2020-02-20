package com.smoothstack.lms.administrator.dao;

import java.sql.Connection;
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
	
	public Integer addPublisher(Connection conn, Publisher publisher) throws SQLException {
		return saveReturnPk(conn, "insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)", 
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone() });
	}

	public Boolean updatePublisher(Connection conn, Publisher publisher) throws SQLException {
		return save(conn, "update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ?" + 
	" where publisherId = ?", new Object[]{ publisher.getPublisherName(), publisher.getPublisherAddress(),
			publisher.getPublisherPhone(), publisher.getPublisherId() }) > 0;
	}

	public Boolean deletePublisher(Connection conn, Integer publisherId) throws SQLException {
		return save(conn, "delete from tbl_publisher where publisherId = ?", new Object[] { publisherId }) > 0;
	}
	
	public List<Publisher> readPublishers(Connection conn) throws SQLException {
		return read(conn, "select * from tbl_publisher", null);
	}
	
	public List<Publisher> readPublisherById(Connection conn, Integer publisherId) throws SQLException {
		return read(conn, "select * from tbl_publisher where publisherId = ?", new Object[] { publisherId });
	}
	
	public Boolean publisherExists(Connection conn, Integer publisherId) throws SQLException {
		return read(conn, "select * from tbl_publisher where publisherId = ?", new Object[] { publisherId }).size() > 0;
	}
	
	@Override
	public List<Publisher> extractData(Connection conn, ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			p.setBooks(bdao.readFirstLevel(conn, "select * from tbl_book where bookId IN "
					+ "(select bookId from tbl_book where pubId = ?)", new Object[] { p.getPublisherId() }));
			publishers.add(p);
		}
		return publishers;
	}

	@Override
	List<Publisher> extractDataFirstLevel(Connection conn, ResultSet rs) throws SQLException {
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
