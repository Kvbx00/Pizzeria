package com.pizzeria.service;

import com.pizzeria.model.OrderDetails;
import com.pizzeria.repository.OrderDetailsRepository;
import com.pizzeria.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private ProductRepository productRepository;

    public void addOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    public OrderDetails getOrderDetailsById(int id) {
        return orderDetailsRepository.findById(id).orElse(null);
    }

    public void updateOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    public void deleteOrderDetails(int id) {
        orderDetailsRepository.deleteById(id);
    }

    public List<OrderDetails> getAllOrders(){
        return orderDetailsRepository.findAll();
    }


}

