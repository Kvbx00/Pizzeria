package com.pizzeriaRestaurant.service;

import com.pizzeriaRestaurant.model.Orders;
import com.pizzeriaRestaurant.repository.ProductRepository;
import com.pizzeriaRestaurant.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Orders> getAllOrders(){
		return ordersRepository.findAll();
	}
	
	public List<Orders> getByEmail(String email){
		 return	 ordersRepository.getByEmail(email);
	}

	public List<Orders> getOrdersByDate(Date keyword) {
		return ordersRepository.findByDate(keyword);
	}
	
	public List<Orders> getOrdersByCategory(String keyword) {
		List<Orders> sOrders = new ArrayList<>();
		List<Integer> productIds = productRepository.getByCategory(keyword);
		if (!productIds.isEmpty()) {
			for (int id : productIds) {
				List<Orders> tempList = ordersRepository.findByproductid(id);
				if (!tempList.isEmpty()) {
					for (Orders p : tempList) {
						sOrders.add(p);
					}
				}
			}
		}
		return sOrders;
	}

	public void deleteOrders(int id) {
		ordersRepository.deleteById(id);
		
	}

	public void addOrders(Orders orders) {
		ordersRepository.save(orders);
	}
}
