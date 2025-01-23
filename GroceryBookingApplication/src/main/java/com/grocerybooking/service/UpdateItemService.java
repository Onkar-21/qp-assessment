package com.grocerybooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public class UpdateItemService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	Log logger= LogFactory.getLog(UpdateItemService.class);
	
	/**
	 * Updates the grocery item with new details if present
	 * @param groceryBookingService
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<GroceryItem> updateGroceryItem(GroceryItem groceryItem) {
		if (null == groceryItemRepository) {
			logger.error("Error while updating GroceryItem");
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		Optional<GroceryItem> groceryItemCheck = groceryItemRepository.findById(groceryItem.getGroceryItemId());
		if (groceryItemCheck.isEmpty()) {
			logger.error("GroceryItem not found");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		logger.info("GroceryItem udated successfully: "+ groceryItem.getGroceryItemId());
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
			logger.error("Error while updateGroceryStock");
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		List<GroceryItem> updatedItemsList = new ArrayList<>();
		
		for (GroceryItem groceryItem : groceryItems) {
			Optional<GroceryItem> groceryItemCheck = groceryItemRepository.findById(groceryItem.getGroceryItemId());
			if (!groceryItemCheck.isEmpty()) {
				groceryItemCheck.get().setGroceryItemAvailableStock(groceryItem.getGroceryItemAvailableStock());
				groceryItemRepository.save(groceryItemCheck.get());
				updatedItemsList.add(groceryItem);
				logger.info("GroceryItems updated successfully");
			}
		}

		return new ResponseEntity<>(updatedItemsList, HttpStatus.OK);
	}

}
