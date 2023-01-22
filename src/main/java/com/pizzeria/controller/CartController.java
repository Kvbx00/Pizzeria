package com.pizzeria.controller;

import com.pizzeria.model.*;
import com.pizzeria.service.CartService;
import com.pizzeria.service.CustomerService;
import com.pizzeria.service.OrderDetailsService;
import com.pizzeria.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrdersService ordersService;
	@Autowired
	private OrderDetailsService orderDetailsService;

    @ExceptionHandler(Exception.class)
    public String handleSqlException(Exception e, HttpSession session) {
        return "redirect:/viewCart";
    }

    @PostMapping("/confirmCart")
    public String addToCart(@RequestParam("quantity") int quantity, HttpSession session) {
        Cart cart = new Cart();
        Product product = (Product) session.getAttribute("product");
        int min = 100;
        int max = 999;
        int b = (int) (Math.random() * (max - min + 1) + min);
        cart.setId(b);
        cart.setProductId(product.getId());
        cart.setName(product.getName());
        cart.setQuantity(quantity);
        cart.setPrice(product.getPrice() * quantity);
        cartService.saveCart(cart);
        session.setAttribute("action", "Produkt został dodany do koszyka");
        float temp = 0;
        if (session.getAttribute("sessionCost") == null) {
            temp = 0;
        } else {
            temp = (float) session.getAttribute("sessionCost");
        }
        float sessionCost = (cart.getPrice() + temp);
        session.setAttribute("sessionCost", sessionCost);
        return "redirect:/menu";
    }

    @GetMapping("/viewCart")
    public String viewCart(Model model, HttpSession session) {
        List<Cart> cartList = cartService.getAllCart();
        if (!cartList.isEmpty()) {
            model.addAttribute("cartList", cartList);
            model.addAttribute("action", session.getAttribute("action"));
            session.setAttribute("action", null);
            return "viewCart";
        } else {
            session.setAttribute("action", "Koszyk jest pusty");
            return "redirect:/menu";
        }
    }

    @GetMapping("/deleteCartProduct/{id}")
    public String deleteById(@PathVariable(value = "id") int id, Model model) {
        cartService.deleteCartId(id);
        model.addAttribute("action", "Produkt został pomyślnie usunięty");
        return "redirect:/viewCart";
    }

    @PostMapping("/buyNow")
    public String buyProducts(HttpSession session) {
        List<Cart> cartList = cartService.getAllCart();
        Orders orders = new Orders();
        String email = (String) session.getAttribute("customerLogin");
        Customer customer = customerService.getCustomer(email);
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        int min = 100000;
        int max = 999999;
        int b = (int) (Math.random() * (max - min + 1) + min);
        orders.setId(b);
        orders.setDate(date);
        orders.setCustomer(customer);
        float totalCost = 0;
        for (Cart cl : cartList) {
			OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProductid(cl.getProductId());
            orderDetails.setName(cl.getName());
            orderDetails.setQuantity(cl.getQuantity());
            orderDetails.setTotalcost(cl.getPrice());
            orderDetails.setOrders(orders);
            totalCost += cl.getPrice();
        }
        orders.setPrice(totalCost);
        ordersService.addOrders(orders);
        for (Cart cl : cartList) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProductid(cl.getProductId());
            orderDetails.setName(cl.getName());
            orderDetails.setQuantity(cl.getQuantity());
            orderDetails.setTotalcost(cl.getPrice());
            orderDetails.setOrders(orders);
            orderDetailsService.addOrderDetails(orderDetails);
        }
		cartService.cartDeleteAll();
        session.setAttribute("action", "Produkty zostały pomyślnie zamówione");
        return "redirect:/menu";
    }

}
