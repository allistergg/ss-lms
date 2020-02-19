package com.ss.lms.librarian.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.librarian.entity.Copy;

@Component
public class CopyDAO extends BaseDAO<Copy> {

    private final String ADD_COPY_SQL = "INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?,?,?)";
    private final String UPDATE_COPY_SQL = "UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?";
    private final String DELETE_COPY_SQL =  "DELETE FROM tbl_book_copies WHERE bookId =? and branchId = ?";
    private final String READ_COPIES_SQL = "SELECT * FROM tbl_book_copies";

    @Autowired
    private BookDAO bdao;
    @Autowired
    private BranchDAO brdao;

    public void addCopy(Copy copy, Connection conn) throws SQLException, ClassNotFoundException {
        save(ADD_COPY_SQL, new Object[] {copy.getBook().getBookId(), copy.getBranch().getBranchId(), copy.getNoOfCopies()}, conn);
    }

    public void updateCopy(Copy copy, Connection conn) throws SQLException, ClassNotFoundException {
        save(UPDATE_COPY_SQL, new Object[] {copy.getNoOfCopies(), copy.getBook().getBookId(), copy.getBranch().getBranchId()}, conn);
    }

    public void deleteCopy(Copy copy, Connection conn) throws SQLException, ClassNotFoundException {
        save(DELETE_COPY_SQL, new Object[] {copy.getBook().getBookId(), copy.getBranch().getBranchId()}, conn);
    }

    public List<Copy> readAllCopies(Connection conn) throws SQLException, ClassNotFoundException {
     return read(READ_COPIES_SQL, null, conn);
    }



    @Override
    List<Copy> extractData(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
      List<Copy> copies = new ArrayList<>();
        while (rs.next()) {
            Copy c = new Copy();
            c.setBook(bdao.getFullBookById(rs.getInt("bookId"), conn));
            c.setBranch(brdao.getBranchById(rs.getInt("branchId"), conn));
            c.setNoOfCopies(rs.getInt("noOfCopies"));
            copies.add(c);
        }
        return copies;
    }

    @Override
    List<Copy> extractDataFirstLevel(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
        return null;
    }
}
