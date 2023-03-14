package com.example.myknitshop.web.controllers;

import com.example.myknitshop.service.MessageService;
import com.example.myknitshop.service.OrderService;
import com.example.myknitshop.service.ProductService;
import com.example.myknitshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final MessageService messageService;

    public UserController(UserService userService, ProductService productService, OrderService orderService, MessageService messageService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.messageService = messageService;
    }

    @GetMapping("/purchase/{id}")
    public String buyProduct(@PathVariable("id") Long productId, Principal username) {
        this.userService.addProductToChoseList(productId, username);
        return "redirect:/product";
    }

    @GetMapping("/user/admin")
    public String adminPanel(Model model){
        model.addAttribute ("allProducts", this.productService.getAllProducts ());
        model.addAttribute ("allOrders", this.orderService.getAllOrders());
        model.addAttribute ("allMessages", this.messageService.getAllMessages());
        return "admin";
    }

}
