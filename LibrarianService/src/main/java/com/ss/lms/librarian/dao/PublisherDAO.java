package com.ss.lms.librarian.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ss.lms.librarian.entity.Publisher;

@Component
public class PublisherDAO extends BaseDAO<Publisher> {

    private String ADD_PUBLISHER_SQL = "INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?,?,?)";
    private String UPDATE_PUBLISHER_SQL = "UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?";
    private String DELETE_PUBLISHER_SQL = "DELETE FROM tbl_publisher WHERE publisherId = ?";
    private String READ_PUBLISHERS_SQL =  "SELECT * FROM tbl_publisher";
    private String GET_PUBLISHER_BY_ID_SQL = "SELECT * FROM tbl_publisher WHERE publisherId = ?";

  public Integer addPublisher(Publisher publisher, Connection conn) throws SQLException, ClassNotFoundException {
        return save(ADD_PUBLISHER_SQL, new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()}, conn);
    }

    public void updatePublisher(Publisher publisher, Connection conn) throws SQLException, ClassNotFoundException {
        save(UPDATE_PUBLISHER_SQL, new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()}, conn);
    }

    public void deletePublisher(Publisher publisher, Connection conn) throws SQLException, ClassNotFoundException {
        save(DELETE_PUBLISHER_SQL, new Object[] {publisher.getPublisherId()}, conn);
    }

    public List<Publisher> readAllPublishers(Connection conn) throws SQLException, ClassNotFoundException {
        return read(READ_PUBLISHERS_SQL, null, conn);
    }

    public Publisher getPublisherById(Integer id, Connection conn) throws SQLException, ClassNotFoundException {
        return (Publisher) read(GET_PUBLISHER_BY_ID_SQL, new Object[] {id}, conn).get(0);
    }

    @Override
    List<Publisher> extractData(ResultSet rs, Connection conn) throws SQLException {
        List<Publisher> publishers = new ArrayList<>();
        while (rs.next()) {
            Publisher p = new Publisher();
            p.setPublisherId(rs.getInt("publisherId"));
            p.setPublisherName(rs.getString("publisherName"));
            p.setPublisherAddress(rs.getString("publisherAddress"));
            p.setPublisherPhone(rs.getString("publisherPhone"));
            publishers.add(p);
        }
        return publishers;
    }

    @Override
    List<Publisher> extractDataFirstLevel(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
        return null;
    }
}
