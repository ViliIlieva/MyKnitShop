package com.example.myknitshop.web.controllers;

import com.example.myknitshop.service.ProductService;
import com.example.myknitshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PageController {

    private final ProductService productService;
    private final UserService userService;

    public PageController(ProductService productService,
                          UserService userService) {
        this.productService = productService;
        this.userService = userService;
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

}
