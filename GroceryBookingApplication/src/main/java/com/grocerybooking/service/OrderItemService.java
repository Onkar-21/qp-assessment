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
import com.grocerybooking.dto.OrderRequestBean;
import com.grocerybooking.dto.OrderResponseBean;
import com.grocerybooking.entity.GroceryItem;
import com.grocerybooking.entity.GroceryOrder;
import com.grocerybooking.entity.PurchasedProduct;
import com.grocerybooking.entity.UserInfo;
import com.grocerybooking.repository.GroceryItemRepository;
import com.grocerybooking.repository.GroceryOrderRepository;
import com.grocerybooking.security.UserInfoService;
import com.grocerybooking.security.UserUtils;

/**
 * A service class for the GroceryBookingApplication
 */
@Service
public class OrderItemService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	@Autowired
	private GroceryOrderRepository groceryOrderRepository;

	@Autowired
	private UserInfoService userInfoService;
	
	Log logger= LogFactory.getLog(OrderItemService.class);
	
	/**
	 * Updates the inventory level or available stock of grocery items if present
	 * @param groceryBookingService
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<OrderResponseBean> orderGroceryItems(List<OrderRequestBean> orderRequestBeans) {
		UserInfo userInfo = userInfoService.findByUserName(UserUtils.getLoggedInUser().getUsername());
		OrderResponseBean orderResponseBean = new OrderResponseBean();
		List<PurchasedProduct> purchasedProducts = new ArrayList<>();
		GroceryOrder groceryOrder = new GroceryOrder();
		
		if (null != userInfo) {
			if (null == groceryItemRepository || null == groceryOrderRepository) {
				logger.error("Error while orderRequestBeans");
				return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
			}
			double bill = 0;
			for (OrderRequestBean orderRequestBean : orderRequestBeans) {
				Optional<GroceryItem> groceryItemCheck = groceryItemRepository.findById(orderRequestBean.getGroceryItemId());
				
				if (groceryItemCheck.isPresent()) {
					GroceryItem groceryItem = groceryItemCheck.get();
					if (groceryItem.getGroceryItemAvailableStock() >= orderRequestBean.getQuantity()) {
						purchasedProducts.add(new PurchasedProduct(groceryItem, orderRequestBean.getQuantity()));
						groceryItem.setGroceryItemAvailableStock(groceryItem.getGroceryItemAvailableStock()-orderRequestBean.getQuantity());
						bill += (groceryItem.getGroceryItemPrice() * orderRequestBean.getQuantity());
						groceryItemRepository.save(groceryItem);
					}
				}
			}
	
			/* If no stock is available for all the ordered items */
			if (purchasedProducts.isEmpty()) {
			    orderResponseBean.setMessage("The ordered quantity is not available in stock.");
			} 
			/* If no stock is available for few of the ordered items */
			else if (purchasedProducts.size() != orderRequestBeans.size()) {
			    orderResponseBean.setMessage("The order placed for few items. The total price for the items is: " + bill);
			} 
			/* If order placed successfully */
			else {
			    orderResponseBean.setMessage("The order placed successfully. The total price for the items is: " + bill);
			}

			groceryOrder.setPurchasedProducts(purchasedProducts);
			groceryOrder.setUserInfo(userInfo);
			orderResponseBean.setGroceryOrder(groceryOrder);
			groceryOrderRepository.save(groceryOrder);
			logger.info("Grocery order placed");

		}
		return new ResponseEntity<>(orderResponseBean, HttpStatus.OK);
	}
}
