package com.example.myknitshop.web.controllers;

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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/purchase/{id}")
    String buyProduct(@PathVariable("id") Long productId, Principal username) {
        this.userService.addProductToBuyList(productId, username);
        return "redirect:/product";
    }

    @GetMapping("/shopping/card/remove-product-from-list/{id}")
    String removeProductFromPurchaseList(@PathVariable("id") Long productId, Principal username){
        this.userService.removeProduct(productId, username);
        return "redirect:/shopping/card";
    }

    @GetMapping("/shopping/card")
    public String shoppingCard(Principal username, Model model) {
        model.addAttribute("cardCashProduct", this.userService.getPurchaseListByUser(username));
        model.addAttribute ("count", this.userService.countOfItemInShopCard (username));
        model.addAttribute ("sumForAllProducts", this.userService.sumForAllPurchaseProduct (username));
        return "shopping-card";
    }
}
