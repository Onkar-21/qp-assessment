package com.grocerybooking.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * A entity class defining the order with cooresponding purchased grocery items
 */
@Entity
@Table(name = "groceryorder")
public class GroceryOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderId")
	long orderId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<PurchasedProduct> purchasedProduct;

	/**
	 * Default/ No-args constructor for GroceryOrder
	 */
	public GroceryOrder() {
		super();
	}

	/**
	 * Constructor for GroceryOrder
	 * @param groceryOrderId
	 * @param purchasedProduct
	 */
	public GroceryOrder(long orderId, List<PurchasedProduct> purchasedProduct) {
		super();
		this.orderId = orderId;
		this.purchasedProduct = purchasedProduct;
	}

	/**
	 * @return orderId
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Sets the orderId
	 * @param orderId
	 */
	public void setOrderId(long groceryOrderId) {
		this.orderId = groceryOrderId;
	}

	/**
	 * @return purchasedProduct
	 */
	public List<PurchasedProduct> getPurchasedProduct() {
		return purchasedProduct;
	}

	/**
	 * Sets the purchasedProduct
	 * @param purchasedProduct
	 */
	public void setPurchasedProduct(List<PurchasedProduct> purchasedProduct) {
		this.purchasedProduct = purchasedProduct;
	}
	
}
