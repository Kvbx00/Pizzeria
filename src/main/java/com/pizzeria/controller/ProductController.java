package com.pizzeria.controller;

import com.pizzeria.model.Product;
import com.pizzeria.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping({"/menu"})
    public String productByPizza(Model model) {
        List<Product> product_appetizer = productRepository.findProductByProduct_category("przystawki");
        List<Product> product_pizza = productRepository.findProductByProduct_category("pizza");
        List<Product> product_pasta = productRepository.findProductByProduct_category("pasta");
        model.addAttribute("product_appetizer", product_appetizer);
        model.addAttribute("product_pizza", product_pizza);
        model.addAttribute("product_pasta", product_pasta);
        return "menu";
    }
}
