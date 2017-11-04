package com.assessment.intellect.exceptions;

/**
 * <p>
 * This exception is thrown when ever any kind of error occurred in user operations
 * </p>
 * @author bhargavmummini@gmail.com
 *
 */
public class UserOperationsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMsg;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public UserOperationsException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public UserOperationsException() {
		super();
	}
	
}
