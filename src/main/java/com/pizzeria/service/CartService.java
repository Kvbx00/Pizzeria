package com.pizzeria.service;

import com.pizzeria.model.Cart;
import com.pizzeria.repository.CartRepository;
import com.pizzeria.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductRepository productRepository;

	public void saveCart(Cart cart) {
		cartRepository.save(cart);		
	}
	
	public List<Cart> getAllCart() {
		return cartRepository.findAll();
	}
	
	public void cartDeleteAll() {
		cartRepository.deleteAll();
	}

	public void deleteCartId(int id) {
		cartRepository.deleteById(id);
	}
}
