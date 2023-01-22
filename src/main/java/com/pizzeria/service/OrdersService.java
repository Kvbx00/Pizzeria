package com.pizzeria.service;

import com.pizzeria.model.Orders;
import com.pizzeria.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersRepository ordersRepository;

	public void deleteOrders(int id) {
		ordersRepository.deleteById(id);
		
	}
	public void addOrders(Orders orders) {
		ordersRepository.save(orders);
	}

	public List<Orders> getAllOrders(){
		return ordersRepository.findAll();
	}

	public List<Orders> getOrdersByEmail(String email) {
		return ordersRepository.findByCustomerEmail(email);
	}
}
