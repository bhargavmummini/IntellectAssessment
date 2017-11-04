/**
 * 
 */
package com.assessment.intellect.services;

import com.assessment.intellect.domain.UserDetails;
import com.assessment.intellect.domain.UserDetailsResponse;
import com.assessment.intellect.exceptions.UserOperationsException;

/**
 * <p>
 * Provides services related to user such as
 * </P>
 * <ul>
 * <li>Creating a new user</li>
 * <li>Updating existing user</li>
 * <li>Deleting an exisxting user</li>
 * </ul>
 * 
 * @author Admin
 *
 */
public interface IUserDetailsService {
	/**
	 * <p>
	 * This services takes {@link UserDetails} as a input and tries to create
	 * it. If user already exists the {@link UserOperationsException} is throw.
	 * If user is create successfully the userID will be returned
	 * </p>
	 * 
	 * @param tableDetails
	 *            {@link UserDetails}
	 * @return {@link UserDetailsResponse}
	 * @throws UserOperationsException
	 */
	UserDetailsResponse createUser(UserDetails tableDetails) throws UserOperationsException;

	/**
	 * <p>
	 * This service update the given details basing on the given userID
	 * </p>
	 * @param tableDetails
	 *            {@link UserDetails}
	 * @return {@link UserDetailsResponse}
	 * @throws UserOperationsException
	 */
	UserDetailsResponse updateUser(UserDetails tableDetails) throws UserOperationsException;

	/**
	 * <p>
	 * This services makes a user inactive if he is active
	 * </p
	 * @param tableDetails
	 *            {@link UserDetails}
	 * @return {@link UserDetailsResponse}
	 * @throws UserOperationsException
	 */
	UserDetailsResponse deleteUser(UserDetails tableDetails) throws UserOperationsException;
}
