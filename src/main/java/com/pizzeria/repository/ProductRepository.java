package com.pizzeria.repository;

import com.pizzeria.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product WHERE product_category = ?1", nativeQuery = true)
    List<Product>findProductByProduct_category(String product_category);
}
