package com.grocerybooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.grocerybooking.entity.GroceryItem;
import com.grocerybooking.repository.GroceryBookingRepository;

/**
 * A service class for the GroceryBookingApplication
 */
@Service
public class GroceryBookingService {

	@Autowired
	public GroceryBookingRepository groceryBookingRepository;
	
	/**
	 * Saves the GroceryItem in the repository
	 * @param groceryItem
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<GroceryItem> saveGroceryItem(GroceryItem groceryItem) {
		if (null == groceryBookingRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		groceryBookingRepository.save(groceryItem);
		return new ResponseEntity<>(groceryItem, HttpStatus.OK);
	}

	/**
	 * Returns all the grocery items from the database as list
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
		if (null == groceryBookingRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}

		List<GroceryItem> allGroceryItems = groceryBookingRepository.findAll();
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
		if (null == groceryBookingRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		GroceryItem groceryItem = groceryBookingRepository.findById(itemId).get();
		if (null != groceryItem) {
			groceryBookingRepository.delete(groceryItem);
		}
		return new ResponseEntity<>(groceryItem, HttpStatus.OK);
	}
	
	/**
	 * Updates the grocery item with new details if present
	 * @param groceryBookingService
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<GroceryItem> updateGroceryItem(GroceryItem groceryItem) {
		if (null == groceryBookingRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		Optional<GroceryItem> groceryItemCheck = groceryBookingRepository.findById(groceryItem.getItemId());
		if (groceryItemCheck.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		GroceryItem groceryItemNew = GroceryItem.updateItem(groceryItem);
		groceryBookingRepository.save(groceryItemNew);
		
		return new ResponseEntity<>(groceryItemNew, HttpStatus.OK);
	}
	
	/**
	 * Updates the inventory level or available stock of grocery items if present
	 * @param groceryBookingService
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<List<GroceryItem>> updateGroceryStock(List<GroceryItem> groceryItems) {
		if (null == groceryBookingRepository) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		List<GroceryItem> updatedItemsList = new ArrayList<>();
		
		for (GroceryItem groceryItem : groceryItems) {
			Optional<GroceryItem> groceryItemCheck = groceryBookingRepository.findById(groceryItem.getItemId());
			if (!groceryItemCheck.isEmpty()) {
				GroceryItem groceryItemNew = new GroceryItem();
				groceryItemNew.setItemId(groceryItem.getItemId());
				groceryItemNew.setItemName(groceryItemCheck.get().getItemName());
				groceryItemNew.setItemPrice(groceryItemCheck.get().getItemPrice());
				groceryItemNew.setAvailableStock(groceryItem.getAvailableStock());
				groceryBookingRepository.save(groceryItemNew);
				updatedItemsList.add(groceryItemCheck.get());
			}
		}

		return new ResponseEntity<>(updatedItemsList, HttpStatus.OK);
	}
}