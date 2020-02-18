package com.ss.lms.librarian.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.librarian.entity.Loan;

@Component
public class LoanDAO extends BaseDAO<Loan> {

    private String ADD_LOAN_SQL = "INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) VALUES (?,?,?,?,?,?)";
    private String UPDATE_LOAN_SQL = "UPDATE tbl_book_loans SET dateOut = ?, dueDate = ?, dateIn = ? WHERE bookId = ? AND branchId = ? AND cardNo = ?";
    private String DELETE_LOAN_SQL = "DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ?";
    private String READ_LOAN_SQL = "SELECT * FROM tbl_book_loans";

    @Autowired
    BookDAO bdao;
    @Autowired
    BranchDAO brdao;
    @Autowired
    BorrowerDAO bodao;
    
    public LoanDAO(Connection conn) {
        super(conn);
    }

    public void addLoan(Loan loan) throws SQLException, ClassNotFoundException {
        save(ADD_LOAN_SQL, new Object[] {loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo(),
                                        loan.getDateOut(), loan.getDueDate(), loan.getDateIn()});
    }

    public void updateLoan(Loan loan) throws SQLException, ClassNotFoundException {
        save(UPDATE_LOAN_SQL, new Object[] {loan.getDateOut(), loan.getDueDate(), loan.getDateIn(),
                                            loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo()});
    }

    public void deleteLoan(Loan loan) throws SQLException, ClassNotFoundException {
        save(DELETE_LOAN_SQL, new Object[] {loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo()});
    }

    public List<Loan> readAllLoans() throws SQLException, ClassNotFoundException {
        return read(READ_LOAN_SQL, null);
    }

    @Override
    List<Loan> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
      
        List<Loan> loans = new ArrayList<>();
        while(rs.next()) {
            Loan l = new Loan();
            l.setBook(bdao.getBookById(rs.getInt("bookId")));
            l.setBranch(brdao.getBranchById(rs.getInt("branchId")));
            l.setBorrower(bodao.getBorrowerByCardNo(rs.getInt("cardNo")));
            l.setDateOut(rs.getDate("dateOut").toLocalDate());
            l.setDueDate(rs.getDate("dueDate").toLocalDate());
            if (rs.getDate("dateIn") != null) {
                l.setDateIn(rs.getDate("dateIn").toLocalDate());
            }
            loans.add(l);
        }
        return loans;
    }

    @Override
    List<Loan> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
        return null;
    }
}
