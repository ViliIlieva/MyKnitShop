package com.example.myknitshop.web.controllers;

import com.example.myknitshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/purchase/{id}")
    String buyProduct(@PathVariable("id") Long productId, Principal username) {
        this.userService.addProductToChoseList(productId, username);
        return "redirect:/product";
    }

}
