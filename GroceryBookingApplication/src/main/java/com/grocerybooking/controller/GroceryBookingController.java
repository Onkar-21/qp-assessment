package com.grocerybooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grocerybooking.dto.AuthRequest;
import com.grocerybooking.dto.OrderRequestBean;
import com.grocerybooking.dto.OrderResponseBean;
import com.grocerybooking.entity.GroceryItem;
import com.grocerybooking.entity.UserInfo;
import com.grocerybooking.security.JwtService;
import com.grocerybooking.service.GroceryBookingService;
import com.grocerybooking.service.UserInfoService;

/**
 * A controller class for the GroceryBookingApplication
 */
@RestController
public class GroceryBookingController {

	private final UserInfoService service; 
    
	private final JwtService jwtService; 
    
    private final AuthenticationManager authenticationManager; 
    
    GroceryBookingController(UserInfoService service, JwtService jwtService, AuthenticationManager authenticationManager) { 
        this.service = service; 
        this.jwtService = jwtService; 
        this.authenticationManager = authenticationManager; 
    }
    
    @PostMapping("/auth/register") 
    public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) { 
        String response = service.addUser(userInfo); 
        return ResponseEntity.status(HttpStatus.CREATED).body(response); 
    }
    
    @PostMapping("/auth/login") 
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) { 
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getUserPassword())); 
        if (authentication.isAuthenticated()) {
        	System.out.println(authentication.getAuthorities());
            String token = jwtService.generateToken(authRequest.getUserName());
            return ResponseEntity.ok(token); 
        } else { 
            throw new UsernameNotFoundException("Invalid user request!"); 
        } 
    }
    
	@Autowired
    private GroceryBookingService groceryBookingService;
	
	/**
	 * Admin Req1: Add new grocery items to the system.
	 * @param groceryItem
	 * @return ResponseEntity<GroceryItem>
	 */
	@PostMapping("/app/admin/saveGroceryItem")
    public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItem groceryItem){
        return groceryBookingService.saveGroceryItem(groceryItem);
    }
	
	/**
	 * Admin Req2: View existing grocery items.
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	@GetMapping("/app/admin/getAllGroceryItems")
	public ResponseEntity<List<GroceryItem>> getAllGroceryItems(){
		return groceryBookingService.getAllGroceryItems();
	}

	/**
	 * Admin Req3: Remove grocery items from the system.
	 * @param itemId
	 * @return ResponseEntity<GroceryItem>
	 */
	@DeleteMapping("/app/admin/removeGroceryItem/{itemId}")
	public ResponseEntity<GroceryItem> removeGroceryItem(@PathVariable Long itemId){
		return groceryBookingService.removeGroceryItem(itemId);
	}

	/**
	 * Admin Req4: Update details (e.g., name, price) of existing grocery items
	 * @param groceryItem
	 * @return ResponseEntity<GroceryItem>
	 */
	@PatchMapping("/app/admin/updateGroceryItem")
	public ResponseEntity<GroceryItem> updateGroceryItem(@RequestBody GroceryItem groceryItem) {
		return groceryBookingService.updateGroceryItem(groceryItem);
	}
	
	/**
	 * Admin Req5: Manage inventory levels of grocery items
	 * @param groceryItem
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	@PatchMapping("/app/admin/updateGroceryStock")
	public ResponseEntity<List<GroceryItem>> updateGroceryStock(@RequestBody List<GroceryItem> groceryItems) {
		return groceryBookingService.updateGroceryStock(groceryItems);
	}

	/**
	 * User Req1: View the list of available grocery items(Without available stock).
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	@GetMapping("/app/user/getAllGroceryItems")
	public ResponseEntity<List<GroceryItem>> viewAllGroceryItems(){
		return groceryBookingService.getAllGroceryItems();
	}
	
	/**
	 * User Req2: Ability to book multiple grocery items in a single order
	 * @param groceryItem
	 * @return ResponseEntity<List<GroceryItem>>
	 */
	@PostMapping("/app/user/orderGroceryItems")
	public ResponseEntity<List<OrderResponseBean>> orderGroceryItems(@RequestBody List<OrderRequestBean> orderRequestBean) {
		return groceryBookingService.orderGroceryItems(orderRequestBean);
	}
	
}
