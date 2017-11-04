package com.assessment.intellect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.intellect.domain.ErrorDetails;
import com.assessment.intellect.domain.UserDetails;
import com.assessment.intellect.domain.UserDetailsResponse;
import com.assessment.intellect.exceptions.UserOperationsException;
import com.assessment.intellect.services.IUserDetailsService;
import com.assessment.intellect.util.UserDetailsConstants;
import com.assessment.intellect.util.UserOperationValidations;

/**
 * <p>
 * This controller exposes user related operation to outside world
 * </p>
 * 
 * @author Admin
 *
 */
@RestController
public class UserController {

	@Autowired
	IUserDetailsService userDetailsService;

	/**
	 * This Controller takes a user details object as a json input and send it
	 * to services for adding in to DB. Add passes back the result from services
	 * 
	 * @param userDetails
	 * @return
	 */
	@RequestMapping(value = "user/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public UserDetailsResponse createUser(@RequestBody UserDetails userDetails) {
		List<ErrorDetails> valError = UserOperationValidations.validateCreateUser(userDetails);
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		if (valError.isEmpty()) {
			try {
				userDetailsResponse = userDetailsService.createUser(userDetails);
				userDetailsResponse.setResMsg(UserDetailsConstants.STATUS_SUCCESS);
				return userDetailsResponse;
			} catch (UserOperationsException e) {
				valError.add(new ErrorDetails(e.getErrorCode(), "", e.getErrorMsg()));
				return getDefaultError(valError);
			}
		} else {
			return getDefaultError(valError);
		}
	}

	/**
	 * If Updates the user with the given details. If user is does not exist the
	 * error will be thrown
	 * 
	 * @param userDetails
	 * @return
	 */
	@RequestMapping(value = "user/update", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public UserDetailsResponse updateUser(@RequestBody UserDetails userDetails) {
		List<ErrorDetails> valError = UserOperationValidations.validateUserID(userDetails);
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		if (valError.isEmpty()) {
			try {
				userDetailsResponse = userDetailsService.updateUser(userDetails);
				userDetailsResponse.setResMsg(UserDetailsConstants.STATUS_SUCCESS);
				return userDetailsResponse;
			} catch (UserOperationsException e) {
				valError.add(new ErrorDetails(e.getErrorCode(), "", e.getErrorMsg()));
				return getDefaultError(valError);
			}
		} else {
			return getDefaultError(valError);
		}
	}

	/**
	 * Deletes the given users
	 * 
	 * @param userDetails
	 * @return
	 */
	@RequestMapping(value = "user/delete", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public UserDetailsResponse deleteUser(@RequestBody UserDetails userDetails) {
		List<ErrorDetails> valError = UserOperationValidations.validateUserID(userDetails);
		if (valError.isEmpty()) {
			UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
			try {
				userDetailsResponse = userDetailsService.deleteUser(userDetails);
				userDetailsResponse.setResMsg(UserDetailsConstants.STATUS_SUCCESS);
				return userDetailsResponse;
			} catch (UserOperationsException e) {
				valError.add(new ErrorDetails(e.getErrorCode(), "", e.getErrorMsg()));
				return getDefaultError(valError);
			}
		} else {
			return getDefaultError(valError);
		}
	}

	/*
	 * This method returns a default response object with error message
	 */
	private UserDetailsResponse getDefaultError(List<ErrorDetails> valError) {
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		userDetailsResponse.setResMsg(UserDetailsConstants.STATUS_ERROR);
		userDetailsResponse.setValError(valError);
		return userDetailsResponse;
	}
}
