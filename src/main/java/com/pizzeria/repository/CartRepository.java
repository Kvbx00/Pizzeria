package com.pizzeria.repository;

import com.pizzeria.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
