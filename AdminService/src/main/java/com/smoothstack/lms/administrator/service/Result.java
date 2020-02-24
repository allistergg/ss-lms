package com.smoothstack.lms.administrator.service;

public class Result<T> {
	
	private Boolean isSuccess;
	private T result;
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}

}
