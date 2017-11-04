package com.assessment.intellect.domain;

public class ErrorDetails {
	private String errorCode;
	private String field;
	private String message;
	public ErrorDetails(String errorCode, String field, String message) {
		super();
		this.errorCode = errorCode;
		this.field = field;
		this.message = message;
	}
	public ErrorDetails() {
		super();
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
