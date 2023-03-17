package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.MakeOrderDTO;
import com.example.myknitshop.models.dto.bindingModels.MessageDTO;
import com.example.myknitshop.service.OrderService;
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
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @ModelAttribute("makeOrderDTO")
    public MakeOrderDTO initMakeOrderDTO(){
        return new MakeOrderDTO ();
    }
    @ModelAttribute("messageAddDTO")
    public MessageDTO initAddProductDTO(){
        return new MessageDTO();
    }

    @GetMapping("/cart/remove-product-from-list/{id}")
    String removeProductFromChoseList(@PathVariable("id") Long productId, Principal username){
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
    public String cart(Principal username,
                       @Valid MakeOrderDTO makeOrderDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("makeOrderDTO", makeOrderDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.makeOrderDTO", bindingResult);

            return "redirect:/cart";
        }
        Long orderId = this.userService.orderProducts (makeOrderDTO, username);
        return "redirect:/order/details/" + orderId;
    }

    @GetMapping("/order/details/{id}")
    public String placeOrder(@PathVariable("id") Long orderId,
                             Principal principal, Model model) {
        model.addAttribute("orderDetails", this.userService.getOrderDetailsById(principal, orderId));
        return "order-details";
    }

    @GetMapping("/order/close/{id}")
    public String closeOrder(@PathVariable("id") Long orderId){
        this.orderService.closeOrder(orderId);
        return "redirect:/user/admin";
    }

    @GetMapping("/orders")
    public String orderByClientId(Principal principal, Model model){
        model.addAttribute("clientOrders", this.userService.getAllOrders(principal));
        model.addAttribute("completedOrders", this.userService.getCompletedOrdersWithoutMessage (principal));
        return "orders";
    }

    @PostMapping("/orders")
    public String addMessage( Principal principal,
                              @Valid MessageDTO messageDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
//TODO да оправя да ми хваща грешките
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("messageDTO", messageDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.messageDTO", bindingResult);

            return "redirect:/orders";
        }
        this.userService.addMessage(messageDTO, principal);
        return "redirect:/orders";
    }
}
