package com.pizzeria.repository;

import com.pizzeria.model.Customer;
import com.pizzeria.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{
    List<Orders> findByCustomer(Customer customer);
    List<Orders> findByOrderDetailsName(String name);
    List<Orders> findByDate(Date date);

}
