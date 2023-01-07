package com.pizzeriaRestaurant.repository;

import com.pizzeriaRestaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findById(int id);
	
	@Query("Select id from Product where category Like %?1%")
	List<Integer> getByCategory(String keyword);
	
	@Query("SELECT p FROM Product p WHERE p.description LIKE %?1%"
			+" OR p.name LIKE %?1%"
			+" OR p.price LIKE %?1%"
			+" OR p.category LIKE %?1%")
	public List<Product> homeSearch(String keyword);
}
