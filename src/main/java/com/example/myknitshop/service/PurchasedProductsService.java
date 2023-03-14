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

    public List<PurchasedProducts> addProducts(List<PurchasedProducts> productsToAddInDB) {
        List<PurchasedProducts> productsInDB = this.purchaseProductsRepository.findAll();

        for (PurchasedProducts productToAdd : productsToAddInDB) {
            productToAdd.setSum(productToAdd.getPrice().multiply(BigDecimal.valueOf(productToAdd.getQuantity())));

            List<PurchasedProducts> sameProducts = productsInDB.stream().filter(p -> p.getImg().equals(productToAdd.getImg())).toList();

            if (!sameProducts.isEmpty()) {
                if (sameProducts.stream().anyMatch(p -> p.getQuantity() == productToAdd.getQuantity())) {
                    productToAdd.setId(sameProducts.stream()
                            .filter(p -> p.getQuantity() == productToAdd.getQuantity())
                            .findFirst().get()
                            .getId());
                } else {
                    this.purchaseProductsRepository.save(productToAdd);
                }
            } else {
                this.purchaseProductsRepository.save(productToAdd);
            }
        }
        return productsToAddInDB;
    }
}



