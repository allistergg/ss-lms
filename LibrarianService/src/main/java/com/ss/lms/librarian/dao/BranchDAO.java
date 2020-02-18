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

    private String ADD_BRANCH_SQL = "INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?,?)";
    private String UPDATE_BRANCH_SQL = "UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?";
    private String DELETE_BRANCH_SQL = "DELETE FROM tbl_library_branch WHERE branchId = ?";
    private String READ_BRANCHES_SQL = "SELECT * FROM tbl_library_branch";
    private String GET_BRANCH_COPIES_SQL = "SELECT * FROM tbl_book_copies WHERE branchId = ?";
    private String GET_BRANCH_LOANS_SQL = "SELECT * FROM tbl_book_loans WHERE branchId = ?";
    private String GET_BRANCH_BY_ID_SQL = "SELECT * FROM tbl_library_branch WHERE branchId = ?";

    
    @Autowired
    LoanDAO ldao;
    @Autowired
    CopyDAO cdao;
    
    public BranchDAO(Connection conn) {
        super(conn);
    }

    public Integer addBranch(Branch branch) throws SQLException, ClassNotFoundException {
        return save(ADD_BRANCH_SQL, new Object[] {branch.getBranchName(), branch.getBranchAddress()});
    }

    public void updateBranch(Branch branch) throws SQLException, ClassNotFoundException {
        save(UPDATE_BRANCH_SQL, new Object[] {branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId()});
    }

    public void deleteBranch(Branch branch) throws SQLException, ClassNotFoundException {
        save(DELETE_BRANCH_SQL, new Object[] {branch.getBranchId()});
    }

    public List<Branch> readAllBranches() throws SQLException, ClassNotFoundException {
        return read(READ_BRANCHES_SQL, null);
    }

    public List<Branch> readAllBranchesFirstLevel() throws SQLException, ClassNotFoundException {
        return readFirstLevel(READ_BRANCHES_SQL, null);
    }

    public Branch getBranchById(Integer id) throws SQLException, ClassNotFoundException {
        try {
        return (Branch) readFirstLevel(GET_BRANCH_BY_ID_SQL, new Object[] {id}).get(0);
    } catch (IndexOutOfBoundsException e) {
        System.out.println("Branch not found");
        return null;
    }
    }
    public Branch getFullBranchById(Integer id) throws SQLException, ClassNotFoundException {
        try{
            return (Branch) read(GET_BRANCH_BY_ID_SQL, new Object[] {id}).get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Branch not found");
            return null;
        }
    }


    @Override
    List<Branch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
    	List<Branch> branches = new ArrayList<>();
        while (rs.next()) {
            Branch b = new Branch();
            b.setBranchId(rs.getInt("branchId"));
            b.setBranchName(rs.getString("branchName"));
            b.setBranchAddress(rs.getString("branchAddress"));
            b.setCopies(cdao.read(GET_BRANCH_COPIES_SQL, new Object[] {b.getBranchId()}));
            b.setLoans(ldao.read(GET_BRANCH_LOANS_SQL, new Object[] {b.getBranchId()}));
            branches.add(b);
        }
        return branches;
    }

    @Override
    List<Branch> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
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
