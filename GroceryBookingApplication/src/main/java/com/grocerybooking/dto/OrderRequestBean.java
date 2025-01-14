package com.grocerybooking.dto;

/**
 * A dto class for transferring data from frontend to backend and withing classes
 */
public class OrderRequestBean {

	long groceryItemId;
	
	int quantity;

	/**
	 * Default/ No-args constructor for OrderRequest
	 */
	public OrderRequestBean() {
		super();
	}

	/**
	 * Constructor for OrderRequest
	 * @param groceryItemId
	 * @param quantity
	 */
	public OrderRequestBean(long groceryItemId, int quantity) {
		super();
		this.groceryItemId = groceryItemId;
		this.quantity = quantity;
	}

	/**
	 * @return groceryItemId
	 */
	public long getGroceryItemId() {
		return groceryItemId;
	}

	/**
	 * Sets the groceryItemId
	 * @param groceryItemId
	 */
	public void setGroceryItemId(long groceryItemId) {
		this.groceryItemId = groceryItemId;
	}

	/**
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
