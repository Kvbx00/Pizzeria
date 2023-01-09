package com.pizzeriaRestaurant.repository;

import com.pizzeriaRestaurant.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{

	
	 @Query("SELECT p FROM Purchase p WHERE p.customer.email LIKE %?1%") 
	 public	 List<Purchase> getByEmail(String email);
	 
	 public List<Purchase> findByDate(Date date);
	 public List<Purchase> findByproductid(int id);
}
