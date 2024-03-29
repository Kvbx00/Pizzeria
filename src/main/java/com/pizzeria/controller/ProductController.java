package com.pizzeria.controller;

import com.pizzeria.model.Product;
import com.pizzeria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/manageProduct")
	public String manageProduct(Model model) {
		model.addAttribute("products",productService.getAllProducts());
		Product product = new Product();
		model.addAttribute("product", product);
		return "manageProduct";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute("product") Product product, Model model, HttpSession session) {
		int min=10000;int max=99999;int b = (int)(Math.random()*(max-min+1)+min);
		product.setId(b);
		productService.addProduct(product);
		session.setAttribute("action","Produkt został pomyślnie dodany");
		model.addAttribute("product", product);
		return "redirect:/manageProduct";
	}
	
	@GetMapping("/showProductUpdate/{id}")
	public String showProductUpdate(@PathVariable(value="id") int id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "update_product";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product, Model model,HttpSession session) {
		productService.addProduct(product);
		session.setAttribute("action","Produkt został pomyślnie zaktualizowany");
		model.addAttribute("product", product);
		return "redirect:/manageProduct";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(value="id") int id,Model model,HttpSession session) {
		productService.deleteProduct(id);
		session.setAttribute("action", "Produkt został pomyślnie usunięty");
		Product product = new Product();
		model.addAttribute("product", product);
		return "redirect:/manageProduct";
	}
}
