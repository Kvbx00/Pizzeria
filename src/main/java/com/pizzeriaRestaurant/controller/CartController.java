package com.pizzeriaRestaurant.controller;

import com.pizzeriaRestaurant.model.Cart;
import com.pizzeriaRestaurant.model.Customer;
import com.pizzeriaRestaurant.model.Orders;
import com.pizzeriaRestaurant.model.Product;
import com.pizzeriaRestaurant.service.CartService;
import com.pizzeriaRestaurant.service.CustomerService;
import com.pizzeriaRestaurant.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@ExceptionHandler(Exception.class)
	public String handleSqlException(Exception e, HttpSession session) {
		session.setAttribute("action", "Wybierz płatność przez zakupem");
		return "redirect:/viewCart";
	}
	@PostMapping("/confirmCart")
	public String addToCart(@RequestParam("quantity") int quantity,HttpSession session) {
		Cart cart = new Cart();
		Product product = (Product) session.getAttribute("product");
		int min=100;int max=999;int b = (int)(Math.random()*(max-min+1)+min);
		cart.setId(b);
		cart.setProductId(product.getId());
		cart.setName(product.getName());
		cart.setQuantity(quantity);
		cart.setPrice(product.getPrice()*quantity);
		cartService.saveCart(cart);
		session.setAttribute("action", "Produkt został dodany do koszyka");
		float temp=0;
		if(session.getAttribute("sessionCost")==null) {
			temp=0;
		}else {
			temp=(float) session.getAttribute("sessionCost");
		}
		float sessionCost=(cart.getPrice()+temp);
		session.setAttribute("sessionCost", sessionCost);
		return "redirect:/menu";
	}
	
	@GetMapping("/viewCart")
	public String viewCart(Model model,HttpSession session) {
		List<Cart> cartList = cartService.getAllCart();
		if(!cartList.isEmpty()) {
		model.addAttribute("cartList", cartList);
		model.addAttribute("action", session.getAttribute("action"));
		session.setAttribute("action", null);
		return "viewCart";
		}else {
			session.setAttribute("action", "Koszyk jest pusty");
			return "redirect:/menu";
		}
	}
	
	@PostMapping("/buyNow")
	public String buyProducts(@RequestParam("pm") String pm, HttpSession session) {
		System.out.println(pm);
		if(pm.equals("yes")) {
			List<Cart> cartList = cartService.getAllCart();
			Orders orders = new Orders();
			String email = (String) session.getAttribute("customerLogin");
			Customer customer = customerService.getCustomer(email);
			for(Cart cl:cartList) {
				java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
				int min=100000;int max=999999;int b = (int)(Math.random()*(max-min+1)+min);
				orders.setId(b);
				orders.setDate(date);
				System.out.println(date);
				orders.setCustomer(customer);
				orders.setProductid(cl.getProductId());
				orders.setName(cl.getName());
				orders.setQuantity(cl.getQuantity());
				orders.setTotalcost(cl.getPrice());
				ordersService.addOrders(orders);
			}
		session.setAttribute("action", "Produkty zostały pomyślnie zamówione");
		return "redirect:/menu";
		}else {
			session.setAttribute("action", "Wykonaj płatność przed sfinalizowaniem zamówienia");
			return "redirect:/viewCart";
		}	
	}
		
}
