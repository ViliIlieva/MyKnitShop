package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.MakeOrderDTO;
import com.example.myknitshop.service.OrderService;
import com.example.myknitshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

//    @GetMapping("/products/order")
//    public String placeOrder() {
//        return "order-details";
//    }

    @GetMapping ("/products/order")
    public String placeOrder(@Valid MakeOrderDTO makeOrderDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal username){
//        if(bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("makeOrderDTO", makeOrderDTO);
//            redirectAttributes.addFlashAttribute(
//                    "org.springframework.validation.BindingResult.makeOrderDTO", bindingResult);
//
//            return "redirect:/products/order";
//        }
        this.userService.orderProducts (makeOrderDTO, username);
        return "order-details";
    }
}
