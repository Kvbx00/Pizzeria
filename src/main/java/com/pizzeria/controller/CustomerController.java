package com.pizzeria.controller;

import com.pizzeria.model.Customer;
import com.pizzeria.model.Orders;
import com.pizzeria.service.AdminService;
import com.pizzeria.service.CartService;
import com.pizzeria.service.CustomerService;
import com.pizzeria.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private CartService cartService;

	@ExceptionHandler(SQLException.class)
	public String handleSqlException(SQLException e, HttpSession session) {
		session.setAttribute("action", "Użytkownik nie może zostać usunięty, dopóki jego zamówienia nie zostaną usunięte.");
		return "redirect:/manageCustomer";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid Customer customer, BindingResult bindingResult, Model model, HttpSession session) {
		List<String> cEmails = customerService.customerEmails();
		boolean notExist = true;
		for(String e : cEmails) {
			if(customer.getEmail().equals(e))
				notExist=false;
		}
		if(notExist) {
			if ((!bindingResult.hasErrors()) && validate(customer.getEmail())) {
				customerService.saveCustomer(customer);
				model.addAttribute("action", "Rejestracja pomyślna, zaloguj się.");
				session.setAttribute("customerLogin", customer.getEmail());
				session.setAttribute("custName", customer.getName());
				cartService.cartDeleteAll();
				return "redirect:/";
			}else {
				return "new_customer";
			}
		}else {
			session.setAttribute("action", "Wprowadzony email już istnieje, zaloguj się.");
			return "redirect:/";
		}

	}

	@PostMapping("/verifyCustLogin")
	public String verifyLogin(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, HttpSession session, Model model) {
		if (!email.isEmpty() || !password.isEmpty()) {
			if(adminService.loginVerify(email,password)) {
				session.setAttribute("uname", email);
				return "adminDashboard";
			}
			if (customerService.loginVerify(email, password)) {
				session.setAttribute("customerLogin", email);
				Customer customer = customerService.getCustomer(email);
				session.setAttribute("custName", customer.getName());
				cartService.cartDeleteAll();
				return "redirect:/";
			} else {
				model.addAttribute("action", "Email lub hasło są nieprawidłowe.");
				return "customer_login";
			}
		} else {
			model.addAttribute("action", "Pola nie mogą być puste");
			return "customer_login";
		}

	}

	@GetMapping("/customerLogout")
	public String customerLogout(HttpSession session) {
		cartService.cartDeleteAll();
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/manageCustomer")
	public String manageCustomer(Model model,HttpSession session) {
		model.addAttribute("action", session.getAttribute("action"));
		session.setAttribute("action", null);
		model.addAttribute("customers", customerService.getAllCustomers());
		return "manageCustomer";
	}

	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id") int id, Model model) {
		customerService.deleteCustomer(id);
		model.addAttribute("action", "Użytkownik został pomyślnie usunięty");
		return "redirect:/manageCustomer";
	}

	@GetMapping("/customerOrders/{email}")
	public String customerOrders(@PathVariable(name = "email") String email, Model model,HttpSession session) {
		List<Orders> sOrders = ordersService.getByEmail(email);
		if(!sOrders.isEmpty()) {
		model.addAttribute("sOrders", sOrders);
		return "customerOrders";
		}else {
			session.setAttribute("action", "Brak zamówień");
			return "redirect:/manageCustomer";
		}
	}
	
	@PostMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("keyword") String keyword,Model model) {
		List<Customer> sCustomer = customerService.searchCustomer(keyword);
		if(sCustomer.isEmpty()) {
			model.addAttribute("action", "Użytkownik nie został znaleziony");
			model.addAttribute("customers", customerService.getAllCustomers());
			return "manageCustomer";
		}else {
			model.addAttribute("searchHeading","Entered Catogery");
			model.addAttribute("sCustomer", sCustomer);
			return "searchCustomer";	
		}
		
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

}
