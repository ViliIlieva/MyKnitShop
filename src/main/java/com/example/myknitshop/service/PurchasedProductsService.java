package com.example.myknitshop.service;

import com.example.myknitshop.models.entity.PurchasedProducts;
import com.example.myknitshop.repository.PurchaseProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedProductsService {
    private final PurchaseProductsRepository purchaseProductsRepository;

    public PurchasedProductsService(PurchaseProductsRepository purchaseProductsRepository) {
        this.purchaseProductsRepository = purchaseProductsRepository;
    }

    public void addProducts(List<PurchasedProducts> products) {
        List<PurchasedProducts> oldProducts = this.purchaseProductsRepository.findAll ();

        for (PurchasedProducts product : products) {
            if(oldProducts.contains (product) && oldProducts.stream().noneMatch (p-> p.getQuantity () == product.getQuantity ())){
                this.purchaseProductsRepository.save (product);
            }else {
                this.purchaseProductsRepository.save (product);
            }
        }
    }
}
