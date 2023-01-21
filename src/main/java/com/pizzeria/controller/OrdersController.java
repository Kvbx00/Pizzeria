package com.pizzeria.controller;

import com.pizzeria.model.Customer;
import com.pizzeria.model.Orders;
import com.pizzeria.repository.OrderDetailsRepository;
import com.pizzeria.repository.OrdersRepository;
import com.pizzeria.service.CustomerService;
import com.pizzeria.service.OrderDetailsService;
import com.pizzeria.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @GetMapping("/deleteOrders/{id}")
    public String deleteOrders(@PathVariable("id") int id, Model model) {
        ordersService.deleteOrders(id);
        model.addAttribute("action", "Zamówienie zostało pomyślnie usunięte");
        return "redirect:/manageOrders";
    }

    @GetMapping("/viewOrders")
    public String getMyOrders(HttpSession session, Model model) {
        String email = (String) session.getAttribute("customerLogin");
        Customer customer = customerService.getCustomer(email);
        List<Orders> ordersList = ordersRepository.findByCustomer(customer);
        model.addAttribute("orders", ordersList);
        return "/viewOrders";
    }


    @GetMapping("/manageOrders")
    public String manageOrders(Model model) {
        model.addAttribute("orders", ordersService.getAllOrders());
        return "/manageOrders";
    }


    @GetMapping("/searchOrders")
    public String searchOrders(@RequestParam("name") String name, Model model) {
        List<Orders> orders = ordersRepository.findByOrderDetailsName(name);
        if (orders.isEmpty()){
            model.addAttribute("action", "Brak zamówień dla podanego produktu");
            model.addAttribute("orders", ordersService.getAllOrders());
            return "manageOrders";
        }else{
            model.addAttribute("orders", orders);
            return "/searchOrders";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    @GetMapping("/searchOrdersDate")
    public String searchOrders(Model model, @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        if(date != null) {
            List<Orders> orders = ordersRepository.findByDate(date);
            if (orders.isEmpty()) {
                model.addAttribute("action", "Brak zamówień w wybranym dniu");
                model.addAttribute("orders", ordersService.getAllOrders());
                return "manageOrders";
            } else {
                model.addAttribute("orders", orders);
                return "/searchOrders";
            }
        } else {
            model.addAttribute("action", "Nie wpisano daty!");
            model.addAttribute("orders", ordersService.getAllOrders());
            return "manageOrders";
        }
    }
}
