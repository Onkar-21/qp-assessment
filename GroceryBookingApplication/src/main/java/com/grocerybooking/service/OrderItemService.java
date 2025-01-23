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
import com.grocerybooking.repository.GroceryItemRepository;
import com.grocerybooking.repository.GroceryOrderRepository;

/**
 * A service class for the GroceryBookingApplication
 */
@Service
public class OrderItemService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	@Autowired
	private GroceryOrderRepository groceryOrderRepository;
	
	Log logger= LogFactory.getLog(OrderItemService.class);
	
	/**
	 * Updates the inventory level or available stock of grocery items if present
	 * @param groceryBookingService
	 * @return ResponseEntity<GroceryItem>
	 */
	public ResponseEntity<List<OrderResponseBean>> orderGroceryItems(List<OrderRequestBean> orderRequestBeans) {
		if (null == groceryItemRepository || null == groceryOrderRepository) {
			logger.error("Error while orderRequestBeans");
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		List<OrderResponseBean> orderResponseBeans = new ArrayList<>();
		List<PurchasedProduct> purchasedProducts = new ArrayList<>();
		GroceryOrder groceryOrder = new GroceryOrder();
		
		for (OrderRequestBean orderRequestBean : orderRequestBeans) {
			Optional<GroceryItem> groceryItemCheck = groceryItemRepository.findById(orderRequestBean.getGroceryItemId());
			
			if (groceryItemCheck.isPresent()) {
				GroceryItem groceryItem = groceryItemCheck.get();
				if (groceryItem.getGroceryItemAvailableStock() >= orderRequestBean.getQuantity()) {
					purchasedProducts.add(new PurchasedProduct(groceryItem, orderRequestBean.getQuantity()));
					groceryItem.setGroceryItemAvailableStock(groceryItem.getGroceryItemAvailableStock()-orderRequestBean.getQuantity());
					groceryItemRepository.save(groceryItem);
					orderResponseBeans.add(new OrderResponseBean(orderRequestBean.getGroceryItemId(), orderRequestBean.getQuantity()));
				}
			}
		}

		if (orderResponseBeans.isEmpty()) {
			logger.error("Error while orderRequestBeans");
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }

		groceryOrder.setPurchasedProducts(purchasedProducts);
		groceryOrderRepository.save(groceryOrder);
		logger.info("Grocery Order updated");
		return new ResponseEntity<>(orderResponseBeans, HttpStatus.OK);
	}
}
