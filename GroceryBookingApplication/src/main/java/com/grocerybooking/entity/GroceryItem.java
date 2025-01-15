package com.grocerybooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * A entity class defining the grocery item or product
 */
@Entity
@Table(name = "groceryitem")
public class GroceryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groceryitem_seq")
	@SequenceGenerator(name = "groceryitem_seq", sequenceName = "groceryitem_seq", allocationSize = 1)
	@Column(name = "groceryItemId")
	private long groceryItemId;
	
	@Column(name = "groceryItemName")
	private String groceryItemName;
	
	@Column(name = "groceryItemPrice")
	private float groceryItemPrice;
	
	@Column(name = "groceryItemAvailableStock")
	private int groceryItemAvailableStock;

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
	 * @param AvailableStock
	 */
	public GroceryItem(long groceryItemId, String groceryItemName, float groceryItemPrice,
			int groceryItemAvailableStock) {
		super();
		this.groceryItemId = groceryItemId;
		this.groceryItemName = groceryItemName;
		this.groceryItemPrice = groceryItemPrice;
		this.groceryItemAvailableStock = groceryItemAvailableStock;
	}
	
	/**
	 * @return the groceryItemId
	 */
	public long getGroceryItemId() {
		return groceryItemId;
	}

	/**
	 * @param groceryItemId the groceryItemId to set
	 */
	public void setGroceryItemId(long groceryItemId) {
		this.groceryItemId = groceryItemId;
	}

	/**
	 * @return the groceryItemName
	 */
	public String getGroceryItemName() {
		return groceryItemName;
	}

	/**
	 * @param groceryItemName the groceryItemName to set
	 */
	public void setGroceryItemName(String groceryItemName) {
		this.groceryItemName = groceryItemName;
	}

	/**
	 * @return the groceryItemPrice
	 */
	public float getGroceryItemPrice() {
		return groceryItemPrice;
	}

	/**
	 * @param groceryItemPrice the groceryItemPrice to set
	 */
	public void setGroceryItemPrice(float groceryItemPrice) {
		this.groceryItemPrice = groceryItemPrice;
	}

	/**
	 * @return the groceryItemAvailableStock
	 */
	public int getGroceryItemAvailableStock() {
		return groceryItemAvailableStock;
	}

	/**
	 * @param groceryItemAvailableStock the groceryItemAvailableStock to set
	 */
	public void setGroceryItemAvailableStock(int groceryItemAvailableStock) {
		this.groceryItemAvailableStock = groceryItemAvailableStock;
	}

	/**
	 * The method to update grocery item details like name and price
	 * @param groceryItem
	 * @return GroceryItem
	 */
	public static GroceryItem updateItem(GroceryItem groceryItem) {
		GroceryItem groceryItemTemp = new GroceryItem();
		groceryItemTemp.setGroceryItemId(groceryItem.getGroceryItemId());
		groceryItemTemp.setGroceryItemName(groceryItem.getGroceryItemName());
		groceryItemTemp.setGroceryItemPrice(groceryItem.getGroceryItemPrice());
		
		return groceryItem;
	}

}
