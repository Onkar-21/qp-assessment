package com.grocerybooking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grocerybooking.dto.OrderRequestBean;
import com.grocerybooking.dto.OrderResponseBean;
import com.grocerybooking.entity.GroceryItem;
import com.grocerybooking.service.GetItemsService;
import com.grocerybooking.service.OrderItemService;

/**
 * A controller class for the User
 */
@RestController
@RequestMapping("/app/user")
public class UserController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private GetItemsService getItemsService;
	
	/**
	 * User Req1: View the list of available grocery items(Without available stock).
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	@GetMapping("/getAllGroceryItems")
	public ResponseEntity<List<GroceryItem>> viewAllGroceryItems(){
		return getItemsService.getAllGroceryItems();
	}
	
	/**
	 * User Req2: Ability to book multiple grocery items in a single order
	 * @param groceryItem
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	@PostMapping("/orderGroceryItems")
	public ResponseEntity<OrderResponseBean> orderGroceryItems(@RequestBody List<OrderRequestBean> orderRequestBean) {
		return orderItemService.orderGroceryItems(orderRequestBean);
	}
}
