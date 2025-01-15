/**
 * 
 */
package com.grocerybooking.service;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		userInfoRepository.save(userInfo);
		return "User Added Successfully";
	}
	
}