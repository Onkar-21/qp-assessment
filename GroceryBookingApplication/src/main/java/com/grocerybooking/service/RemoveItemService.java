package com.grocerybooking.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.grocerybooking.entity.GroceryItem;
import com.grocerybooking.repository.GroceryItemRepository;

/**
 * A service class for the GroceryBookingApplication
 */
@Service
public class RemoveItemService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	Log logger= LogFactory.getLog(RemoveItemService.class);
	
	/**
	 * Removes the grocery item from the database if present
	 * @param itemId
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<GroceryItem> removeGroceryItem(long itemId) {
		if (null == groceryItemRepository) {
			logger.error("Error while removing GroceryItem");
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		GroceryItem groceryItem = groceryItemRepository.findById(itemId).get();
		if (null != groceryItem) {
			logger.info("GroceryItem deleted with id: " + groceryItem.getGroceryItemId());
			groceryItemRepository.delete(groceryItem);
		}
		return new ResponseEntity<>(groceryItem, HttpStatus.OK);
	}
	
}
