/**
 * 
 */
package com.grocerybooking.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.grocerybooking.entity.UserInfoDetails;

public class UserUtils {

	public static UserInfoDetails getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserInfoDetails) {
                return (UserInfoDetails) principal;
            }
        }
        return null;
    }
	
}
