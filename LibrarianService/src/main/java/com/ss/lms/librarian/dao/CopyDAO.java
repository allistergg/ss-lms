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

    private String ADD_COPY_SQL = "INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?,?,?)";
    private String UPDATE_COPY_SQL = "UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?";
    private String DELETE_COPY_SQL =  "DELETE FROM tbl_book_copies WHERE bookId =? and branchId = ?";
    private String READ_COPIES_SQL = "SELECT * FROM tbl_book_copies";

    @Autowired
    BookDAO bdao;
    @Autowired
    BranchDAO brdao;
    
    public CopyDAO(Connection conn) {
        super(conn);
    }

    public void addCopy(Copy copy) throws SQLException, ClassNotFoundException {
        save(ADD_COPY_SQL, new Object[] {copy.getBook().getBookId(), copy.getBranch().getBranchId(), copy.getNoOfCopies()});
    }

    public void updateCopy(Copy copy) throws SQLException, ClassNotFoundException {
        save(UPDATE_COPY_SQL, new Object[] {copy.getNoOfCopies(), copy.getBook().getBookId(), copy.getBranch().getBranchId()});
    }

    public void deleteCopy(Copy copy) throws SQLException, ClassNotFoundException {
        save(DELETE_COPY_SQL, new Object[] {copy.getBook().getBookId(), copy.getBranch().getBranchId()});
    }

    public List<Copy> readAllCopies() throws SQLException, ClassNotFoundException {
     return read(READ_COPIES_SQL, null);
    }



    @Override
    List<Copy> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
      List<Copy> copies = new ArrayList<>();
        while (rs.next()) {
            Copy c = new Copy();
            c.setBook(bdao.getFullBookById(rs.getInt("bookId")));
            c.setBranch(brdao.getBranchById(rs.getInt("branchId")));
            c.setNoOfCopies(rs.getInt("noOfCopies"));
            copies.add(c);
        }
        return copies;
    }

    @Override
    List<Copy> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
        return null;
    }
}
