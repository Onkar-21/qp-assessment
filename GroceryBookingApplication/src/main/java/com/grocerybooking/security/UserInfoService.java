package com.grocerybooking.security;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grocerybooking.entity.UserInfo;
import com.grocerybooking.entity.UserInfoDetails;
import com.grocerybooking.repository.UserInfoRepository;

/**
 * A service class for the user information
 */
@Service
public class UserInfoService implements UserDetailsService {

	private final UserInfoRepository userInfoRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserInfoService(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
		this.userInfoRepository = userInfoRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userDetail = userInfoRepository.findByUserName(username);
		// Converting userDetail to UserDetails 
		return userDetail.map(UserInfoDetails::new) 
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}
	
	public String addUser(UserInfo userInfo) {
	    userInfo.setUserPassword(passwordEncoder.encode(userInfo.getUserPassword()));
	    try {
	        userInfoRepository.save(userInfo);
	        return "User added successfully.";
	    } catch (DataIntegrityViolationException e) {
	        // Handle duplicate entry (e.g., unique constraint violation)
	        if (e.getCause() instanceof ConstraintViolationException) {
	            return "User with the same username/email already exists.";
	        }
	        return "Failed to add user due to data integrity violation.";
	    } catch (Exception e) {
	        // Handle other unexpected exceptions
	        return "An unexpected error occurred: " + e.getMessage();
	    }
	}

	/**
	 * @return
	 */
	public UserInfo findByUserName(String username) {
		Optional<UserInfo> userDetail = userInfoRepository.findByUserName(username);
		if (null != userDetail) {
			return userDetail.get();
		}
		return null;
	}
	
	
}