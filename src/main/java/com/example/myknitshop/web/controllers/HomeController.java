package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.UserRegistrationDTO;
import com.example.myknitshop.models.dto.viewModels.ProductsViewOnHomePageDTO;
import com.example.myknitshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("homePageProductDTO")
    public ProductsViewOnHomePageDTO initHomePageProductsDTO(){
        return new ProductsViewOnHomePageDTO();
    }

    @GetMapping("/")
    public String loggedOutIndex (Model model){
        model.addAttribute("products",  this.productService.getAllProducts());
        return "index";
    }

}
