package com.grocerybooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grocerybooking.entity.GroceryItem;

/**
 * A repository interface for the GroceryBookingApplication
 */
@Repository
public interface GroceryBookingRepository extends JpaRepository<GroceryItem, Long> {

}
