package com.example.myknitshop.service;

import com.example.myknitshop.repository.PurchaseProductsRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchasedProductsService {
    private final PurchaseProductsRepository purchaseProductsRepository;

    public PurchasedProductsService(PurchaseProductsRepository purchaseProductsRepository) {
        this.purchaseProductsRepository = purchaseProductsRepository;
    }
}
