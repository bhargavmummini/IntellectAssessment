package com.assessment.intellect.util;

import java.util.HashMap;
import java.util.Map;

import com.assessment.intellect.domain.UserDetails;

/**
 * This class contains a static Set for storing user details
 * @author Admin
 *
 */
public class UserDetailsDB {
	private static Map<String, UserDetails> userDetailsDB;
	private UserDetailsDB(){
		/**
		 * An empty private constructor for not allowing this class to create objects
		 */
	}
	public static Map<String, UserDetails> getUserDetailsDB() {
		if(userDetailsDB == null){
			userDetailsDB = new HashMap<>();
		}
		return userDetailsDB;
	}
	public static void setUserDetailsDB(Map<String, UserDetails> userDetailsDB) {
		UserDetailsDB.userDetailsDB = userDetailsDB;
	}
	
}
