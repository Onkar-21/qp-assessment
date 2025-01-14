package com.grocerybooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.grocerybooking.dto.OrderRequestBean;
import com.grocerybooking.dto.OrderResponseBean;
import com.grocerybooking.entity.GroceryItem;
import com.grocerybooking.entity.GroceryOrder;
import com.grocerybooking.entity.PurchasedProduct;
import com.grocerybooking.repository.GroceryItemRepository;
import com.grocerybooking.repository.GroceryOrderRepository;

/**
 * A service class for the GroceryBookingApplication
 */
@Service
public class GroceryBookingService {

	@Autowired
	public GroceryItemRepository groceryItemRepository;

	@Autowired
	public GroceryOrderRepository groceryOrderRepository;
	
	/**
	 * Saves the GroceryItem in the repository
	 * @param groceryItem
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<GroceryItem> saveGroceryItem(GroceryItem groceryItem) {
		if (null == groceryItemRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		groceryItemRepository.save(groceryItem);
		return new ResponseEntity<>(groceryItem, HttpStatus.OK);
	}

	/**
	 * Returns all the grocery items from the database as list
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
		if (null == groceryItemRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}

		List<GroceryItem> allGroceryItems = groceryItemRepository.findAll();
//		if (null != allGroceryItems && !allGroceryItems.isEmpty()) {
			return new ResponseEntity<>(allGroceryItems, HttpStatus.OK);
//		}
//		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	/**
	 * Removes the grocery item from the database if present
	 * @param itemId
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<GroceryItem> removeGroceryItem(long itemId) {
		if (null == groceryItemRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		GroceryItem groceryItem = groceryItemRepository.findById(itemId).get();
		if (null != groceryItem) {
			groceryItemRepository.delete(groceryItem);
		}
		return new ResponseEntity<>(groceryItem, HttpStatus.OK);
	}
	
	/**
	 * Updates the grocery item with new details if present
	 * @param groceryBookingService
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<GroceryItem> updateGroceryItem(GroceryItem groceryItem) {
		if (null == groceryItemRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		Optional<GroceryItem> groceryItemCheck = groceryItemRepository.findById(groceryItem.getItemId());
		if (groceryItemCheck.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		GroceryItem groceryItemNew = GroceryItem.updateItem(groceryItem);
		groceryItemRepository.save(groceryItemNew);
		
		return new ResponseEntity<>(groceryItemNew, HttpStatus.OK);
	}
	
	/**
	 * Updates the inventory level or available stock of grocery items if present
	 * @param groceryBookingService
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<List<GroceryItem>> updateGroceryStock(List<GroceryItem> groceryItems) {
		if (null == groceryItemRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		List<GroceryItem> updatedItemsList = new ArrayList<>();
		
		for (GroceryItem groceryItem : groceryItems) {
			Optional<GroceryItem> groceryItemCheck = groceryItemRepository.findById(groceryItem.getItemId());
			if (!groceryItemCheck.isEmpty()) {
				GroceryItem groceryItemNew = new GroceryItem();
				groceryItemNew.setItemId(groceryItem.getItemId());
				groceryItemNew.setItemName(groceryItemCheck.get().getItemName());
				groceryItemNew.setItemPrice(groceryItemCheck.get().getItemPrice());
				groceryItemNew.setAvailableStock(groceryItem.getAvailableStock());
				groceryItemRepository.save(groceryItemNew);
				updatedItemsList.add(groceryItemCheck.get());
			}
		}

		return new ResponseEntity<>(updatedItemsList, HttpStatus.OK);
	}

	/**
	 * Updates the inventory level or available stock of grocery items if present
	 * @param groceryBookingService
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<List<OrderResponseBean>> orderGroceryItems(List<OrderRequestBean> OrderRequestBeans) {
		if (null == groceryItemRepository || null == groceryOrderRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		List<OrderResponseBean> orderResponseBeans = new ArrayList<>();
		List<PurchasedProduct> purchasedProducts = new ArrayList<>();
		
		for (OrderRequestBean orderRequestBean : OrderRequestBeans) {
			Optional<GroceryItem> groceryItemCheck = groceryItemRepository.findById(orderRequestBean.getGroceryItemId());
			
			if (!groceryItemCheck.isEmpty()) {
				if (groceryItemCheck.get().getAvailableStock() >= orderRequestBean.getQuantity()) {
					purchasedProducts.add(new PurchasedProduct(groceryItemCheck.get(), orderRequestBean.getQuantity()));
					
					orderResponseBeans.add(new OrderResponseBean(orderRequestBean.getGroceryItemId(), orderRequestBean.getQuantity()));
				}
			}
		}
		GroceryOrder groceryOrder = new GroceryOrder();
		groceryOrder.setPurchasedProduct(purchasedProducts);
		groceryOrderRepository.save(groceryOrder);
		
		return new ResponseEntity<>(orderResponseBeans, HttpStatus.OK);
	}
}
