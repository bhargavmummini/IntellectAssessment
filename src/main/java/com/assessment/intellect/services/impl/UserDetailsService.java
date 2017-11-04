/**
 * 
 */
package com.assessment.intellect.services.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.assessment.intellect.domain.UserDetails;
import com.assessment.intellect.domain.UserDetailsResponse;
import com.assessment.intellect.exceptions.UserOperationsException;
import com.assessment.intellect.services.IUserDetailsService;
import com.assessment.intellect.util.UserDetailsConstants;
import com.assessment.intellect.util.UserDetailsDB;

/**
 * @author Admin
 *
 */
@Service
public class UserDetailsService implements IUserDetailsService {

	/* (non-Javadoc)
	 * @see com.assessment.intellect.services.IUserDetailsService#createUser(com.assessment.intellect.domain.UserDetails)
	 */
	@Override
	public UserDetailsResponse createUser(UserDetails userDetails) throws UserOperationsException {
		Map<String, UserDetails> userDetailsList = UserDetailsDB.getUserDetailsDB();
		if(userDetailsList.containsKey(userDetails.getEmail())){
			throw new UserOperationsException(UserDetailsConstants.USER_EXISTS_CODE, UserDetailsConstants.USER_EXISTS);
		}
		userDetails.setActive(true);
		userDetailsList.put(userDetails.getEmail(), userDetails);
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		userDetailsResponse.setUserID(userDetails.getEmail());
		return userDetailsResponse;
	}

	/* (non-Javadoc)
	 * @see com.assessment.intellect.services.IUserDetailsService#updateUser(com.assessment.intellect.domain.UserDetails)
	 */
	@Override
	public UserDetailsResponse updateUser(UserDetails userDetails) throws UserOperationsException {
		Map<String, UserDetails> userDetailsList = UserDetailsDB.getUserDetailsDB();
		if(isUserExists(userDetails.getUserID())){
			UserDetails existingUser = userDetailsList.get(userDetails.getUserID());
			existingUser.setBirthDate(userDetails.getBirthDate());
			existingUser.setPinCode(userDetails.getPinCode());
			userDetailsList.put(userDetails.getUserID(), existingUser);
			
			UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
			userDetailsResponse.setUserID(userDetails.getUserID());
			return userDetailsResponse;
		}else{
			throw new UserOperationsException(UserDetailsConstants.USER_EXISTS_CODE, UserDetailsConstants.USER_EXISTS);
		}
	}

	/* (non-Javadoc)
	 * @see com.assessment.intellect.services.IUserDetailsService#deleteUser(com.assessment.intellect.domain.UserDetails)
	 */
	@Override
	public UserDetailsResponse deleteUser(UserDetails userDetails) throws UserOperationsException {
		Map<String, UserDetails> userDetailsList = UserDetailsDB.getUserDetailsDB();
		if(isUserExists(userDetails.getUserID())){
			UserDetails existingUser = userDetailsList.get(userDetails.getUserID());
			existingUser.setActive(false);
			userDetailsList.put(userDetails.getUserID(), existingUser);
			
			UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
			userDetailsResponse.setUserID(userDetails.getUserID());
			return userDetailsResponse;
		}else{
			throw new UserOperationsException(UserDetailsConstants.USER_NOT_EXISTS_CODE, UserDetailsConstants.USER_NOT_EXISTS);
		}
	}
	private boolean isUserExists(String userID) throws UserOperationsException{
		Map<String, UserDetails> userDetailsList = UserDetailsDB.getUserDetailsDB();
		if(!userDetailsList.containsKey(userID)){
			throw new UserOperationsException(UserDetailsConstants.USER_NOT_EXISTS_CODE, UserDetailsConstants.USER_NOT_EXISTS);
		} else if(!userDetailsList.get(userID).isActive()){
			throw new UserOperationsException(UserDetailsConstants.USER_NOT_EXISTS_CODE, UserDetailsConstants.USER_NOT_EXISTS);
		}
		return true;
	}
}
