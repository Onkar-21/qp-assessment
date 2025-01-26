package com.grocerybooking.entity;

import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * A entity class defining the order with cooresponding purchased grocery items
 */
@Entity
@Table(name = "groceryorder")
public class GroceryOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groceryorder_seq")
	@SequenceGenerator(name = "groceryorder_seq", sequenceName = "groceryorder_seq", allocationSize = 1)
	@Column(name = "groceryOrderId")
	private long groceryOrderId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	@JsonIgnore
	private UserInfo userInfo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "groceryOrderId", referencedColumnName = "groceryOrderId")
	private List<PurchasedProduct> purchasedProducts;

	/**
	 * Default/ No-args constructor for GroceryOrder
	 */
	public GroceryOrder() {
		super();
	}

	/**
	 * @param groceryOrderId
	 * @param purchasedProducts
	 */
	public GroceryOrder(long groceryOrderId, List<PurchasedProduct> purchasedProducts) {
		super();
		this.groceryOrderId = groceryOrderId;
		this.purchasedProducts = purchasedProducts;
	}

	/**
	 * @return the groceryOrderId
	 */
	public long getGroceryOrderId() {
		return groceryOrderId;
	}

	/**
	 * @param groceryOrderId the groceryOrderId to set
	 */
	public void setGroceryOrderId(long groceryOrderId) {
		this.groceryOrderId = groceryOrderId;
	}

	/**
	 * @return the userInfo
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @return the purchasedProducts
	 */
	public List<PurchasedProduct> getPurchasedProducts() {
		return purchasedProducts;
	}

	/**
	 * @param purchasedProducts the purchasedProducts to set
	 */
	public void setPurchasedProducts(List<PurchasedProduct> purchasedProducts) {
		this.purchasedProducts = purchasedProducts;
	}

}
