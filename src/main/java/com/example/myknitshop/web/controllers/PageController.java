package com.example.myknitshop.web.controllers;

import com.example.myknitshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final ProductService productService;

    public PageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/gallery")
    public String gallery(Model model) {
        model.addAttribute ("img", this.productService.getAllProductImage());
        return "gallery";
    }

    @GetMapping("/service")
    public String service() {
        return "service";
    }


}
