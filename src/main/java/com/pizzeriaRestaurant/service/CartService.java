package com.pizzeriaRestaurant.service;

import com.pizzeriaRestaurant.model.Cart;
import com.pizzeriaRestaurant.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;

	public void saveCart(Cart cart) {
		cartRepository.save(cart);		
	}
	
	public List<Cart> getAllCart() {
		return cartRepository.findAll();
	}
	
	public void cartDeleteAll() {
		cartRepository.deleteAll();
	}
}
