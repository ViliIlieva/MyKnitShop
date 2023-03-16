package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.AddProductDTO;
import com.example.myknitshop.models.dto.bindingModels.EditProductDTO;
import com.example.myknitshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @ModelAttribute("editProductDTO")
    public EditProductDTO initEditProductDTO(){
        return new EditProductDTO();
    }

    @GetMapping("/products/add")
    public String productsAdd(){
        return "product-add";
    }

    @PostMapping("/products/add")
    public String productsAdd(@Valid AddProductDTO addProductDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addProductDTO", bindingResult);

            return "redirect:/products/add";
        }
        this.productService.addProduct(addProductDTO);
        return "redirect:/product";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Long productId, Model model){
        model.addAttribute("productToEdit", this.productService.getProductInfoById(productId));
        return "product-edit";
    }

    @PatchMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Long productId,
                              @Valid EditProductDTO editProductDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editProductDTO", editProductDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.editProductDTO", bindingResult);

            return "redirect:/product/edit/" + productId;
        }

        this.productService.editProduct(productId, editProductDTO);
        return "redirect:/user/admin";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId){
        this.productService.deleteProductById(productId);
        return "redirect:/user/admin";
    }

    @GetMapping("/product/info/{id}")
    public String productInfo (@PathVariable("id") Long id, Model model){
        model.addAttribute ("productInfo", this.productService.getProductInfoById(id));
        return "product-info";
    }

    @GetMapping("/product")
    public String allProductsPage (Model model){
        model.addAttribute("allProducts",  this.productService.getAllProductsToViewOnHomePage ());
        return "product";
    }
}
