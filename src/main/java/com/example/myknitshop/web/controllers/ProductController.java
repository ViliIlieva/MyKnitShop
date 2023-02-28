package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.AddProductDTO;
import com.example.myknitshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @ModelAttribute("addProductDTO")
    public AddProductDTO initAddProductDTO(){
        return new AddProductDTO();
    }

    @GetMapping("/products/add")
    public String products(){
        return "product-add";
    }

    @PostMapping("/products/add")
    public String products(@Valid AddProductDTO addProductDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        //TODO да проверя дали юзъра е логнат

        if(bindingResult.hasErrors() || this.productService.addProduct(addProductDTO)){
            redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addProductDTO", bindingResult);

            return "redirect:/products/add";
        }

        return "redirect:/product";
    }
}
