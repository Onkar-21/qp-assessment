package com.grocerybooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grocerybooking.entity.GroceryItem;
import com.grocerybooking.entity.GroceryOrder;

/**
 * A GroceryOrder repository interface for the GroceryBookingApplication
 */
@Repository
public interface GroceryOrderRepository extends JpaRepository<GroceryOrder, Long> {

}
