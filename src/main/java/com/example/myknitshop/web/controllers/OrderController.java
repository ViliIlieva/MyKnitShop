package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.MakeOrderDTO;
import com.example.myknitshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class OrderController {
    private final UserService userService;

    public OrderController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("makeOrderDTO")
    public MakeOrderDTO initMakeOrderDTO(){
        return new MakeOrderDTO ();
    }

    @GetMapping("/cart/remove-product-from-list/{id}")
    String removeProductFromPurchaseList(@PathVariable("id") Long productId, Principal username){
        this.userService.removeProductFromChoseList (productId, username);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Principal username, Model model){
        model.addAttribute("cartCashProduct", this.userService.getChoseListByUserToViewInShoppingCard(username));
        model.addAttribute ("count", this.userService.countOfItemInCart(username));
        model.addAttribute ("sumForAllProducts", this.userService.sumForAllPurchaseProduct (username));
        return "cart";
    }

    @PatchMapping("/cart")
    public String cart(Principal username, Model model,
                       @Valid MakeOrderDTO makeOrderDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("makeOrderDTO", makeOrderDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.makeOrderDTO", bindingResult);

            return "redirect:/cart";
        }

        model.addAttribute("cartCashProduct", this.userService.getChoseListByUserToViewInShoppingCard (username));
        model.addAttribute ("count", this.userService.countOfItemInCart(username));
        model.addAttribute ("sumForAllProducts", this.userService.sumForAllPurchaseProduct (username));


        this.userService.orderProducts (makeOrderDTO, username);
        return "order-details";
    }


    @GetMapping("/order/details")
    public String placeOrder(Principal principal, Model model) {
        return "order-details";
    }
}
