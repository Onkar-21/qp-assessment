package com.grocerybooking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * A entity class defining the purchased grocery items and its quantity
 */
@Entity
@Table(name = "purchasedproduct")
public class PurchasedProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "purchaseId")
	long purchaseId;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	GroceryItem groceryItem;
	
	@Column(name = "purchasedQuantity")
	int purchasedQuantity;

	/**
	 * Default/ No-args constructor for PurchasedProduct
	 */
	public PurchasedProduct() {
		super();
	}

	/**
	 * @param groceryItem
	 * @param purchasedQuantity
	 */
	public PurchasedProduct(GroceryItem groceryItem, int purchasedQuantity) {
		super();
		this.groceryItem = groceryItem;
		this.purchasedQuantity = purchasedQuantity;
	}
	
	/**
	 * Constructor for PurchasedProduct
	 * @param purchaseId
	 * @param groceryItem
	 * @param purchasedQuantity
	 */
	public PurchasedProduct(long purchaseId, GroceryItem groceryItem, int purchasedQuantity) {
		super();
		this.purchaseId = purchaseId;
		this.groceryItem = groceryItem;
		this.purchasedQuantity = purchasedQuantity;
	}

	/**
	 * @return purchaseId
	 */
	public long getPurchaseId() {
		return purchaseId;
	}

	/**
	 * Sets the purchaseId
	 * @param purchaseId
	 */
	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	/**
	 * @return groceryItem
	 */
	public GroceryItem getGroceryItem() {
		return groceryItem;
	}

	/**
	 * Sets the groceryItem
	 * @param groceryItem
	 */
	public void setGroceryItem(GroceryItem groceryItem) {
		this.groceryItem = groceryItem;
	}

	/**
	 * @return purchasedQuantity
	 */
	public int getPurchasedQuantity() {
		return purchasedQuantity;
	}

	/**
	 * Sets the purchasedQuantity
	 * @param purchasedQuantity
	 */
	public void setPurchasedQuantity(int purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}
	
}
