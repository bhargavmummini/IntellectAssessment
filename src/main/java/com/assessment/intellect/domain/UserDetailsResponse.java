/**
 * 
 */
package com.assessment.intellect.domain;

import java.util.List;

/**
 * <p>
 * This class holds response data for user operations
 * </p>
 * <ul>
 * <li>resMsg : Concise response message, indicating what has happened to the request/li>
 * <li>userID : Unique user ID, Created during create operation. For any Update/Delete operations this should be used</li>
 * <li>valError : A list objects indication individual error messages for fields</li>
 * </ul>
 * @author bhargavmummini@gmail.com
 *
 */
public class UserDetailsResponse {
	private String resMsg;
	private String userID;
	private List<ErrorDetails> valError;
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public List<ErrorDetails> getValError() {
		return valError;
	}
	public void setValError(List<ErrorDetails> valError) {
		this.valError = valError;
	}
	
}
