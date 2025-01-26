package com.grocerybooking.dto;

import com.grocerybooking.entity.GroceryOrder;

/**
 * A dto class for transferring data from frontend to backend and withing classes
 */
public class OrderResponseBean {

	private String message;
	
	private GroceryOrder groceryOrder; 
	
	/**
	 * Default/ No-args constructor for OrderRequest
	 */
	public OrderResponseBean() {
		super();
	}

	/**
	 * @param groceryOrderId
	 * @param groceryOrder
	 */
	public OrderResponseBean(String message, GroceryOrder groceryOrder) {
		super();
		this.message = message;
		this.groceryOrder = groceryOrder;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the groceryOrder
	 */
	public GroceryOrder getGroceryOrder() {
		return groceryOrder;
	}

	/**
	 * @param groceryOrder the groceryOrder to set
	 */
	public void setGroceryOrder(GroceryOrder groceryOrder) {
		this.groceryOrder = groceryOrder;
	}

}
