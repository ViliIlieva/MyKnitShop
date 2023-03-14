package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.viewModels.messages.MessagesViewOnHomePageView;
import com.example.myknitshop.models.dto.viewModels.products.ProductsViewOnHomePage;
import com.example.myknitshop.service.MessageService;
import com.example.myknitshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private final ProductService productService;
    private final MessageService messageService;

    public HomeController(ProductService productService, MessageService messageService) {
        this.productService = productService;
        this.messageService = messageService;
    }

    @ModelAttribute("homePageProductDTO")
    public ProductsViewOnHomePage initHomePageProductsDTO(){
        return new ProductsViewOnHomePage();
    }

    @ModelAttribute("messagesDTO")
    public MessagesViewOnHomePageView initMessagesDTO(){
        return new MessagesViewOnHomePageView();
    }

    @GetMapping("/")
    public String loggedOutIndex (Model model){
        model.addAttribute("products",  this.productService.getAllProductsToViewOnHomePage ());
        model.addAttribute("messages", this.messageService.getAllMessagesViewOnHomePage ());
        return "index";
    }

}
