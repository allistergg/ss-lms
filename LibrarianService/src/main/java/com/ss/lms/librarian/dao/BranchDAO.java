package com.ss.lms.librarian.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.librarian.entity.Branch;

@Component
public class BranchDAO extends BaseDAO<Branch> {

    private final String ADD_BRANCH_SQL = "INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?,?)";
    private final String UPDATE_BRANCH_SQL = "UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?";
    private final String DELETE_BRANCH_SQL = "DELETE FROM tbl_library_branch WHERE branchId = ?";
    private final String READ_BRANCHES_SQL = "SELECT * FROM tbl_library_branch";
    private final String GET_BRANCH_COPIES_SQL = "SELECT * FROM tbl_book_copies WHERE branchId = ?";
    private final String GET_BRANCH_LOANS_SQL = "SELECT * FROM tbl_book_loans WHERE branchId = ?";
    private final String GET_BRANCH_BY_ID_SQL = "SELECT * FROM tbl_library_branch WHERE branchId = ?";

    
    @Autowired
    private LoanDAO ldao;
    @Autowired
    private CopyDAO cdao;
    
    public Integer addBranch(Branch branch, Connection conn) throws SQLException, ClassNotFoundException {
        return save(ADD_BRANCH_SQL, new Object[] {branch.getBranchName(), branch.getBranchAddress()}, conn);
    }

    public void updateBranch(Branch branch, Connection conn) throws SQLException, ClassNotFoundException {
        save(UPDATE_BRANCH_SQL, new Object[] {branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId()}, conn);
    }

    public void deleteBranch(Branch branch, Connection conn) throws SQLException, ClassNotFoundException {
        save(DELETE_BRANCH_SQL, new Object[] {branch.getBranchId()}, conn);
    }

    public List<Branch> readAllBranches(Connection conn) throws SQLException, ClassNotFoundException {
        return read(READ_BRANCHES_SQL, null, conn);
    }

    public List<Branch> readAllBranchesFirstLevel(Connection conn) throws SQLException, ClassNotFoundException {
        return readFirstLevel(READ_BRANCHES_SQL, null, conn);
    }

    public Branch getBranchById(Integer id, Connection conn) throws SQLException, ClassNotFoundException {
        try {
        return (Branch) readFirstLevel(GET_BRANCH_BY_ID_SQL, new Object[] {id}, conn).get(0);
    } catch (IndexOutOfBoundsException e) {
   
        return null;
    }
    }
    public Branch getFullBranchById(Integer id, Connection conn) throws SQLException, ClassNotFoundException {
        try{
            return (Branch) read(GET_BRANCH_BY_ID_SQL, new Object[] {id}, conn).get(0);
        } catch (IndexOutOfBoundsException e) {
        
            return null;
        }
    }


    @Override
    List<Branch> extractData(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
    	List<Branch> branches = new ArrayList<>();
        while (rs.next()) {
            Branch b = new Branch();
            b.setBranchId(rs.getInt("branchId"));
            b.setBranchName(rs.getString("branchName"));
            b.setBranchAddress(rs.getString("branchAddress"));
            b.setCopies(cdao.read(GET_BRANCH_COPIES_SQL, new Object[] {b.getBranchId()}, conn));
            b.setLoans(ldao.read(GET_BRANCH_LOANS_SQL, new Object[] {b.getBranchId()}, conn));
            branches.add(b);
        }
        return branches;
    }

    @Override
    List<Branch> extractDataFirstLevel(ResultSet rs, Connection conn) throws SQLException, ClassNotFoundException {
        List<Branch> branches = new ArrayList<>();
        while (rs.next()) {
            Branch b = new Branch();
            b.setBranchId(rs.getInt("branchId"));
            b.setBranchName(rs.getString("branchName"));
            b.setBranchAddress(rs.getString("branchAddress"));
            branches.add(b);
        }
        return branches;
    }
}
