package com.grocerybooking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grocerybooking.entity.GroceryItem;
import com.grocerybooking.service.AddItemService;
import com.grocerybooking.service.GetItemsService;
import com.grocerybooking.service.RemoveItemService;
import com.grocerybooking.service.UpdateItemService;

/**
 * A controller class for the GroceryBookingApplication
 */
@RestController
@RequestMapping("/app/admin")
public class AdminController {
    
	@Autowired
	private AddItemService addItemService;

	@Autowired
	private GetItemsService getItemsService;
	
	@Autowired
	private RemoveItemService removeItemService;
	
    @Autowired
    private UpdateItemService updateItemService;
	
	/**
	 * Admin Req1: Add new grocery items to the system.
	 * @param groceryItem
	 * @return ResponseEntity<GroceryItem>
	 */
	@PostMapping("/saveGroceryItem")
    public ResponseEntity<GroceryItem> saveGroceryItem(@RequestBody GroceryItem groceryItem){
        return addItemService.saveGroceryItem(groceryItem);
    }
	
	/**
	 * Admin Req2: View existing grocery items.
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	@GetMapping("/getAllGroceryItems")
	public ResponseEntity<List<GroceryItem>> getAllGroceryItems(){
		return getItemsService.getAllGroceryItems();
	}

	/**
	 * Admin Req3: Remove grocery items from the system.
	 * @param itemId
	 * @return ResponseEntity<GroceryItem>
	 */
	@DeleteMapping("/removeGroceryItem/{itemId}")
	public ResponseEntity<GroceryItem> removeGroceryItem(@PathVariable Long itemId){
		return removeItemService.removeGroceryItem(itemId);
	}

	/**
	 * Admin Req4: Update details (e.g., name, price) of existing grocery items
	 * @param groceryItem
	 * @return ResponseEntity<GroceryItem>
	 */
	@PatchMapping("/updateGroceryItem")
	public ResponseEntity<GroceryItem> updateGroceryItem(@RequestBody GroceryItem groceryItem) {
		return updateItemService.updateGroceryItem(groceryItem);
	}
	
	/**
	 * Admin Req5: Manage inventory levels of grocery items
	 * @param groceryItem
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	@PatchMapping("/updateGroceryStock")
	public ResponseEntity<List<GroceryItem>> updateGroceryStock(@RequestBody List<GroceryItem> groceryItems) {
		return updateItemService.updateGroceryStock(groceryItems);
	}
	
}
