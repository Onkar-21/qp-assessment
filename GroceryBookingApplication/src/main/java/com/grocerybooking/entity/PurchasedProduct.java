package com.grocerybooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * A entity class defining the purchased grocery items and its quantity
 */
@Entity
@Table(name = "purchasedproduct")
public class PurchasedProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchasedproduct_seq")
	@SequenceGenerator(name = "purchasedproduct_seq", sequenceName = "purchasedproduct_seq", allocationSize = 1)
	@Column(name = "purchasedProductId")
	@JsonIgnore
	private long purchasedProductId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "groceryItemId", referencedColumnName = "groceryItemId")
	private GroceryItem groceryItem;
	
	@Column(name = "purchasedProductQuantity")
	private int purchasedProductQuantity;

	/**
	 * Default/ No-args constructor for PurchasedProduct
	 */
	public PurchasedProduct() {
		super();
	}

	/**
	 * @param groceryItem
	 * @param purchasedProductQuantity
	 */
	public PurchasedProduct(GroceryItem groceryItem, int purchasedProductQuantity) {
		super();
		this.groceryItem = groceryItem;
		this.purchasedProductQuantity = purchasedProductQuantity;
	}

	/**
	 * @param purchasedProductId
	 * @param groceryItem
	 * @param purchasedProductQuantity
	 */
	public PurchasedProduct(long purchasedProductId, GroceryItem groceryItem, int purchasedProductQuantity) {
		super();
		this.purchasedProductId = purchasedProductId;
		this.groceryItem = groceryItem;
		this.purchasedProductQuantity = purchasedProductQuantity;
	}

	/**
	 * @return the purchasedProductId
	 */
	public long getPurchasedProductId() {
		return purchasedProductId;
	}

	/**
	 * @param purchasedProductId the purchasedProductId to set
	 */
	public void setPurchasedProductId(long purchasedProductId) {
		this.purchasedProductId = purchasedProductId;
	}

	/**
	 * @return the groceryItem
	 */
	public GroceryItem getGroceryItem() {
		return groceryItem;
	}

	/**
	 * @param groceryItem the groceryItem to set
	 */
	public void setGroceryItem(GroceryItem groceryItem) {
		this.groceryItem = groceryItem;
	}

	/**
	 * @return the purchasedProductQuantity
	 */
	public int getPurchasedProductQuantity() {
		return purchasedProductQuantity;
	}

	/**
	 * @param purchasedProductQuantity the purchasedProductQuantity to set
	 */
	public void setPurchasedProductQuantity(int purchasedProductQuantity) {
		this.purchasedProductQuantity = purchasedProductQuantity;
	}

}
