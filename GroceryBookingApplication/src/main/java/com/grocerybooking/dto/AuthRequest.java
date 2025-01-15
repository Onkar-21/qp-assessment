/**
 * 
 */
package com.grocerybooking.dto;

/**
 * 
 */
public class AuthRequest {

	private String userName;
    
	private String userPassword;

	/**
	 * 
	 */
	public AuthRequest() {
		super();
	}

	/**
	 * @param userName
	 * @param userPassword
	 */
	public AuthRequest(String userName, String userPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
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

}
