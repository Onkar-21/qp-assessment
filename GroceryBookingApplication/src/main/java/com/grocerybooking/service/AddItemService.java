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
public class AddItemService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	Log logger= LogFactory.getLog(AddItemService.class);
	
	/**
	 * Saves the GroceryItem in the repository
	 * @param groceryItem
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<GroceryItem> saveGroceryItem(GroceryItem groceryItem) {
		if (null == groceryItemRepository) {
			logger.error("Error while saving GroceryItem");
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		GroceryItem groceryItem2= groceryItemRepository.save(groceryItem);
		logger.info("GroceryItem saved with id: "+ groceryItem2.getGroceryItemId());
		return new ResponseEntity<>(groceryItem2, HttpStatus.OK);
	}

}
