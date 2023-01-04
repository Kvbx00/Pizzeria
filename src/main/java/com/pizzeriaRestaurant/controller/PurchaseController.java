package com.pizzeriaRestaurant.controller;

import com.pizzeriaRestaurant.model.Purchase;
import com.pizzeriaRestaurant.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@GetMapping("/managePurchase")
	public String managePurchase(Model model) {
		model.addAttribute("purchases", purchaseService.getAllPurchases());
		return "managePurchase";
	}
	
	@PostMapping("/searchPurchaseDate")
	public String searchPurchaseDate(@RequestParam("keyword") String keyword,Model model) {
		Date date=null;
		try {
		date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(keyword).getTime());
		}catch(Exception e) { System.out.println(e); }
		List<Purchase> sPurchase = purchaseService.getPurchaseByDate(date);
		if(sPurchase.isEmpty()) {
			model.addAttribute("action", "Brak zamówień w wybranym dniu");
			model.addAttribute("purchases", purchaseService.getAllPurchases());
			return "managePurchase";
		}else {
			model.addAttribute("searchHeading","selected Date");
			model.addAttribute("sPurchase", sPurchase);
			return "searchPurchase";	
		}
		
	}
	
	@PostMapping("/searchPurchaseCategory")
	public String searchPurchaseCategory(@RequestParam("keyword") String keyword,Model model) {
		List<Purchase> sPurchase = purchaseService.getPurchaseByCategory(keyword);
		if(sPurchase.isEmpty()) {
			model.addAttribute("action", "Brak zamówień w wybranej kategorii");
			model.addAttribute("purchases", purchaseService.getAllPurchases());
			return "managePurchase";
		}else {
			model.addAttribute("searchHeading","Entered Catogery");
			model.addAttribute("sPurchase", sPurchase);
			return "searchPurchase";	
		}
		
	}
	
	@GetMapping("/deletePurchase/{id}")
	public String deletePurchase(@PathVariable("id") int id,Model model) {
		purchaseService.deletePurchase(id);
		model.addAttribute("action", "Zamówienie zostało pomyślnie usunięte");
		return "redirect:/managePurchase";
	}
}
