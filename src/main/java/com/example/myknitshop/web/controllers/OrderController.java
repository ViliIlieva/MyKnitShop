package com.example.myknitshop.web.controllers;

import com.example.myknitshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/products/order")
    public String orderProducts(Principal username, Model model) {
        this.orderService.orderProducts(username);
        return "order-details";
    }
}
