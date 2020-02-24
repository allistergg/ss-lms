package com.smoothstack.lms.administrator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.administrator.dao.BorrowerDAO;
import com.smoothstack.lms.administrator.model.Borrower;

@Service
public class BorrowerService {
	
	@Autowired
	BorrowerDAO bodao;
	
	// CREATE BORROWER
	public Borrower saveBorrower(Borrower borrower) {
		return bodao.save(borrower);
	}
	
	// READ BORROWER BY CARD NO
	public Result<Borrower> getBorrowerByCardNo(Integer cardNo) {
		Result<Borrower> rs = new Result<Borrower>();
		if (bodao.existsById(cardNo)) {
			rs.setResult(bodao.findById(cardNo).get());
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}	
	
	// READ BORROWERS
	public List<Borrower> readBorrowers() {
		return bodao.findAll();
	}
	
	// UPDATE BORROWER
	public Result<Void> updateBorrower(Borrower borrower) {
		Result<Void> rs = new Result<Void>();
		if (bodao.existsById(borrower.getCardNo())) {
			bodao.save(borrower);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}
	
	// DELETE BORROWER
	public Result<Void> deleteBorrower(Integer cardNo) {
		Result<Void> rs = new Result<Void>();
		if (bodao.existsById(cardNo)) {
			bodao.deleteById(cardNo);
			rs.setIsSuccess(true);
		} else {
			rs.setIsSuccess(false);
		}
		return rs;
	}

}
