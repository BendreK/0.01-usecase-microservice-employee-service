package com.hcl.exception;

import java.util.List;

public class ErrorResponse {

	String errorCode;
	List<String> errorMessage;
	
	
	
	
	public ErrorResponse(String errorCode, List<String> errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}




	public String getErrorCode() {
		return errorCode;
	}




	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}




	public List<String> getErrorMessage() {
		return errorMessage;
	}




	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}


}
