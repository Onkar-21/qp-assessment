/**
 * 
 */
package com.grocerybooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * A entity class defining the user
 */
@Entity
@Table(name = "userinfo")
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userinfo_seq")
	@SequenceGenerator(name = "userinfo_seq", sequenceName = "userinfo_seq", allocationSize = 1)
	@Column(name = "userId")
    private long userId;
	
	@Column(name = "userName", unique = true)
    private String userName;
    
	@Column(name = "userEmail")
    private String userEmail;
    
	@Column(name = "userPassword")
    private String userPassword;
	
	@Column(name = "userRoles")
    private String userRoles;

	/**
	 * 
	 */
	public UserInfo() {
		super();
	}

	/**
	 * @param userId
	 * @param userName
	 * @param userEmail
	 * @param userPassword
	 * @param userRoles
	 */
	public UserInfo(long userId, String userName, String userEmail, String userPassword, String userRoles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRoles = userRoles;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the userRoles
	 */
	public String getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
	
}
