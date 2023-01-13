package com.pizzeriaRestaurant.controller;

import com.pizzeriaRestaurant.model.Orders;
import com.pizzeriaRestaurant.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/manageOrders")
    public String manageOrders(Model model) {
        model.addAttribute("orders", ordersService.getAllOrders());
        return manageOrders(1, "name", "asc", model);
    }

    @GetMapping("/manageOrders/{pageNo}")
    public String manageOrders(@PathVariable(value = "pageNo") int pageNo,
                               @RequestParam("sortField") String sortField,
                               @RequestParam("sortDir") String sortDir,
                               Model model) {
        int pageSize = 5;
        Page<Orders> manageOrders = ordersService.manageOrders(pageNo, pageSize, sortField, sortDir);
        List<Orders> orders = manageOrders.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", manageOrders.getTotalPages());
        model.addAttribute("totalItems", manageOrders.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("orders", orders);
        return "manageOrders";
    }

    @PostMapping("/searchOrdersDate")
    public String searchOrdersDate(@RequestParam("keyword") String keyword, Model model) {
        Date date = null;
        try {
            date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(keyword).getTime());
        } catch (Exception e) {
            System.out.println(e);
        }
        List<Orders> sOrders = ordersService.getOrdersByDate(date);
        if (sOrders.isEmpty()) {
            model.addAttribute("action", "Brak zamówień w wybranym dniu");
            model.addAttribute("orders", ordersService.getAllOrders());
            return "manageOrders";
        } else {
            model.addAttribute("searchHeading", "selected Date");
            model.addAttribute("sOrders", sOrders);
            return "searchOrders";
        }

    }

    @PostMapping("/searchOrdersCategory")
    public String searchOrdersCategory(@RequestParam("keyword") String keyword, Model model) {
        List<Orders> sOrders = ordersService.getOrdersByCategory(keyword);
        if (sOrders.isEmpty()) {
            model.addAttribute("action", "Brak zamówień w wybranej kategorii");
            model.addAttribute("orders", ordersService.getAllOrders());
            return "manageOrders";
        } else {
            model.addAttribute("searchHeading", "Entered Catogery");
            model.addAttribute("sOrders", sOrders);
            return "searchOrders";
        }

    }

    @GetMapping("/deleteOrders/{id}")
    public String deleteOrders(@PathVariable("id") int id, Model model) {
        ordersService.deleteOrders(id);
        model.addAttribute("action", "Zamówienie zostało pomyślnie usunięte");
        return "redirect:/manageOrders";
    }
}
