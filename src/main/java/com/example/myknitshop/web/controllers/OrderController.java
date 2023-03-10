package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.MakeOrderDTO;
import com.example.myknitshop.service.OrderService;
import com.example.myknitshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        this.userService.removeProduct(productId, username);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Principal username, Model model){
        model.addAttribute("cartCashProduct", this.userService.getPurchaseListByUserToViewInShoppingCard (username));
        model.addAttribute ("count", this.userService.countOfItemInCart(username));
        model.addAttribute ("sumForAllProducts", this.userService.sumForAllPurchaseProduct (username));
        return "cart";
    }

    @PostMapping("/cart")
    public String cart(Principal username, Model model,
                       @Valid MakeOrderDTO makeOrderDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("makeOrderDTO", makeOrderDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.makeOrderDTO", bindingResult);

            return "redirect:/products/order";
        }
//TODO да преценя дали трябва и тук да добавя модел Атрибутите, защото ако има грешка като ми редиректне да ги виждам и тях

//        model.addAttribute("cartCashProduct", this.userService.getPurchaseListByUserToViewInShoppingCard (username));
//        model.addAttribute ("count", this.userService.countOfItemInCart(username));
//        model.addAttribute ("sumForAllProducts", this.userService.sumForAllPurchaseProduct (username));

        return "cart";
    }


    @GetMapping("/products/order")
    public String placeOrder() {
        return "order-details";
    }

//    @PostMapping("/products/order")
//    public String placeOrder(@Valid MakeOrderDTO makeOrderDTO,
//                             BindingResult bindingResult,
//                             RedirectAttributes redirectAttributes,
//                             Principal username){
//this.userService.orderProducts (makeOrderDTO, username);
//        return "order-details";
//    }
}
