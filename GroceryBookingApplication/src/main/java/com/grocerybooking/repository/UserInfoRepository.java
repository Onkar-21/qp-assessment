/**
 * 
 */
package com.grocerybooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocerybooking.entity.UserInfo;

/**
 * A UserInfo repository interface for the GroceryBookingApplication
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> { 
    Optional<UserInfo> findByUserName(String username); 
}