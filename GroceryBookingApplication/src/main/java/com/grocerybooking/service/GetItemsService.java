package com.grocerybooking.service;

import java.util.ArrayList;
import java.util.List;
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
public class GetItemsService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	Log logger= LogFactory.getLog(GetItemsService.class);
	
	/**
	 * Returns all the grocery items from the database as list
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
		if (null == groceryItemRepository) {
			logger.error("Error while all the grocery items");
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}

		List<GroceryItem> allGroceryItems = groceryItemRepository.findAll();
		if (allGroceryItems != null && !allGroceryItems.isEmpty()) {
			logger.info("Grocery items retrives successful");
			return new ResponseEntity<>(allGroceryItems, HttpStatus.OK);
		}
		logger.info("No data found in the grocery");
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}

}
