package com.grocerybooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * A entity class defining the grocery item or product
 */
@Entity
public class GroceryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long itemId;
	
	@Column(name = "itemname")
	String itemName;
	
	@Column(name = "itemprice")
	float itemPrice;
	
	@Column(name = "availablestock")
	int availableStock;

	
	/**
	 * Default/ No-args constructor for GroceryItem
	 */
	public GroceryItem() {
		super();
	}

	/**
	 * Constructor for GroceryItem
	 * @param itemId
	 * @param itemName
	 * @param itemPrice
	 * @param availableStock
	 */
	public GroceryItem(long itemId, String itemName, float itemPrice, int availableStock) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.availableStock = availableStock;
	}

	/**
	 * @return itemId
	 */
	public long getItemId() {
		return itemId;
	}

	/**
	 * Sets the itemId
	 * @param itemId
	 */
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Sets the itemName
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return itemPrice
	 */
	public float getItemPrice() {
		return itemPrice;
	}

	/**
	 * Sets the itemPrice
	 * @param itemPrice
	 */
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	/**
	 * @return availableStock
	 */
	public int getAvailableStock() {
		return availableStock;
	}

	/**
	 * Sets the availableStock
	 * @param availableStock
	 */
	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	/**
	 * The method to update grocery item details like name and price
	 * @param groceryItem
	 * @return GroceryItem
	 */
	public static GroceryItem updateItem(GroceryItem groceryItem) {
		GroceryItem groceryItemTemp = new GroceryItem();
		groceryItemTemp.setItemId(groceryItem.getItemId());
		groceryItemTemp.setItemName(groceryItem.getItemName());
		groceryItemTemp.setItemPrice(groceryItem.getItemPrice());
		
		return groceryItem;
	}

}
