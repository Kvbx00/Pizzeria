package com.pizzeria.service;

import com.pizzeria.model.Orders;
import com.pizzeria.repository.ProductRepository;
import com.pizzeria.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public Page<Orders> manageOrders(int pageNo, int pageSize, String sortField, String sortDirection){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.ordersRepository.findAll(pageable);
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
