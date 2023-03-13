package com.example.myknitshop.service;

import com.example.myknitshop.models.entity.PurchasedProducts;
import com.example.myknitshop.repository.PurchaseProductsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PurchasedProductsService {
    private final PurchaseProductsRepository purchaseProductsRepository;

    public PurchasedProductsService(PurchaseProductsRepository purchaseProductsRepository) {
        this.purchaseProductsRepository = purchaseProductsRepository;
    }

    public List<PurchasedProducts> addProducts(List<PurchasedProducts> products) {
        List<PurchasedProducts> oldProducts = this.purchaseProductsRepository.findAll ();

        for (PurchasedProducts product : products) {
            product.setSum (product.getPrice ().multiply (BigDecimal.valueOf (product.getQuantity ())));
            if(oldProducts.contains (product)){
               if(oldProducts.stream().noneMatch (p-> p.getQuantity () == product.getQuantity ())){
                   this.purchaseProductsRepository.save (product);
               }
                product.setId (oldProducts.stream ()
                        .filter (p->p.getImg ().equals (product.getImg ()))
                        .findFirst ().get ()
                        .getId ());
            }else {
                this.purchaseProductsRepository.save (product);
            }

        }
        return products;
    }
}
