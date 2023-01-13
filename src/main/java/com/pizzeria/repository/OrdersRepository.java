package com.pizzeria.repository;

import com.pizzeria.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	
	 @Query("SELECT p FROM Orders p WHERE p.customer.email LIKE %?1%")
	 public	 List<Orders> getByEmail(String email);
	 
	 public List<Orders> findByDate(Date date);
	 public List<Orders> findByproductid(int id);
}
