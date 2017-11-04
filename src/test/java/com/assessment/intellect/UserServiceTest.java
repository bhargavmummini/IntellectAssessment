package com.assessment.intellect;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.assessment.intellect.domain.UserDetails;
import com.assessment.intellect.domain.UserDetailsResponse;
import com.assessment.intellect.exceptions.UserOperationsException;
import com.assessment.intellect.services.IUserDetailsService;
import com.assessment.intellect.util.UserDetailsConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
	@Autowired
	IUserDetailsService userDetailsService;

	@Test
	public void testCreateUser() {
		UserDetails userDetails = new UserDetails();
		try {
			userDetails.setEmail("bhargavmummini@gmail.com");
			userDetails.setBirthDate("10-Jun-2017");
			userDetails.setfName("Bhagrav");
			userDetails.setlName("Mummini");
			userDetails.setPinCode("600096");
			UserDetailsResponse userResponse = userDetailsService.createUser(userDetails);

			assertEquals(userDetails.getEmail(), userResponse.getUserID());
			try {
				userResponse = userDetailsService.createUser(userDetails);
				fail("Duplicate User Inserted");
			} catch (UserOperationsException e) {
				assertEquals(UserDetailsConstants.USER_EXISTS, e.getErrorMsg());
			}

			assertEquals(userDetails.getEmail(), userResponse.getUserID());
			userDetails = new UserDetails();
			userDetails.setEmail("bhargavmummini2@gmail.com");
			userDetails.setBirthDate("10-Jun-2017");
			userDetails.setfName("Bhagrav");
			userDetails.setlName("Mummini");
			userDetails.setPinCode("600096");
			userResponse = userDetailsService.createUser(userDetails);

			assertEquals(userDetails.getEmail(), userResponse.getUserID());

		} catch (UserOperationsException e) {
			e.printStackTrace();
			fail("Unable to Create user");
		}
	}

	@Test
	public void testUpdateUser() {
		UserDetails userDetails = new UserDetails();
		try {
			
			userDetails.setUserID("bhargavmummini2@gmail.com");
			userDetails.setBirthDate("11-Jun-2017");
			userDetails.setPinCode("600096");
			UserDetailsResponse userResponse = userDetailsService.updateUser(userDetails);

			assertEquals(userDetails.getUserID(), userResponse.getUserID());

			try {
				userResponse = userDetailsService.deleteUser(userDetails);
				assertEquals(userDetails.getUserID(), userResponse.getUserID());
				userResponse = userDetailsService.updateUser(userDetails);
				fail("Failed to Update User");
			} catch (UserOperationsException e) {
				assertEquals(UserDetailsConstants.USER_NOT_EXISTS, e.getErrorMsg());
			}
			userDetails = new UserDetails();
			userDetails.setUserID("bhargavmummini@gmail.com");
			userDetails.setBirthDate("11-Jun-2017");
			userDetails.setPinCode("600096");
			userResponse = userDetailsService.updateUser(userDetails);

			assertEquals(userDetails.getUserID(), userResponse.getUserID());

		} catch (UserOperationsException e) {
			e.printStackTrace();
			fail("Unable to Update user");
		}
	}

	@Test
	public void testZDeleteUser() {
		// fail("Not yet implemented");
	}

}
