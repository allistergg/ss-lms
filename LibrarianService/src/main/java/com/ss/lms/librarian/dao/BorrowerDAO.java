package com.ss.lms.librarian.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.librarian.entity.Borrower;

@Component
public class BorrowerDAO extends BaseDAO<Borrower> {

    private String ADD_BORROWER_SQL = "INSERT INTO tbl_borrower (name, address, phone) VALUES (?,?,?)";
    private String UPDATE_BORROWER_SQL = "UPDATE tbl_borrower name = ?, address = ?, phone = ? WHERE cardNO = ?)";
    private String DELETE_BORROWER_SQL = "DELETE FROM tbl_borrower WHERE cardNo = ?";
    private String READ_BORROWERS_SQL = "SELECT * FROM tbl_borrower";
    private String GET_BORROWER_LOANS_SQL = "SELECT * FROM tbl_book_loans WHERE cardNo = ?";
    private String GET_BORROWER_BY_ID_SQL = "SELECT * FROM tbl_borrower WHERE cardNo = ?";

    @Autowired
    LoanDAO ldao;
    
    public BorrowerDAO(Connection conn) {
        super(conn);
    }

    public Integer addBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
        return save(ADD_BORROWER_SQL, new Object[]{borrower.getName(), borrower.getAddress(), borrower.getPhone()});
    }

    public void updateBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
        save(UPDATE_BORROWER_SQL, new Object[]{borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo()});
    }

    public void deleteBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
        save(DELETE_BORROWER_SQL, new Object[]{borrower.getCardNo()});
    }

    public List<Borrower> readAllBorrowers() throws SQLException, ClassNotFoundException {
        return read(READ_BORROWERS_SQL, null);
    }

    public List<Borrower> readAllBorrowersFirstLevel() throws SQLException, ClassNotFoundException {
        return readFirstLevel(READ_BORROWERS_SQL, null);
    }

    public Borrower getBorrowerByCardNo(Integer cardNo) throws SQLException, ClassNotFoundException {
        return (Borrower) readFirstLevel(GET_BORROWER_BY_ID_SQL, new Object[] {cardNo}).get(0);
    }
    public Borrower getFullBorrowerByCardNo(Integer cardNo) throws SQLException, ClassNotFoundException {
        try {
            return (Borrower) read(GET_BORROWER_BY_ID_SQL, new Object[]{cardNo}).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


    @Override
    List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
       
    	List<Borrower> borrowers = new ArrayList<>();
        while (rs.next()) {
            Borrower bo = new Borrower();
            bo.setCardNo(rs.getInt("cardNo"));
            bo.setName(rs.getString("name"));
            bo.setAddress(rs.getString("address"));
            bo.setPhone(rs.getString("phone"));
            bo.setLoans(ldao.read(GET_BORROWER_LOANS_SQL, new Object[]{bo.getCardNo()}));
            borrowers.add(bo);
        }
        return borrowers;
    }

    @Override
    List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Borrower> borrowers = new ArrayList<>();
        while (rs.next()) {
            Borrower bo = new Borrower();
            bo.setCardNo(rs.getInt("cardNo"));
            bo.setName(rs.getString("name"));
            bo.setAddress(rs.getString("address"));
            bo.setPhone(rs.getString("phone"));
            borrowers.add(bo);
        }
        return borrowers;
    }
}

