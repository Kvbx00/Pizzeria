package com.pizzeria.controller;

import com.pizzeria.repository.ProductRepository;
import com.pizzeria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository pRepo;
    @GetMapping({"/menu"})
    public ModelAndView getAllProducts(){
        ModelAndView mav = new ModelAndView("menu");
        mav.addObject("product", pRepo.findAll());
        return mav;
    }
}
