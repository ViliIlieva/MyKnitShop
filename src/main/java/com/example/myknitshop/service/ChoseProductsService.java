package com.example.myknitshop.service;

import com.example.myknitshop.models.entity.ChoseProducts;
import com.example.myknitshop.repository.ChoseProductsRepository;
import org.springframework.stereotype.Service;

@Service
public class ChoseProductsService {
    private final ChoseProductsRepository choseProductsRepository;

    public ChoseProductsService(ChoseProductsRepository choseProductsRepository) {
        this.choseProductsRepository = choseProductsRepository;
    }

    public void save(ChoseProducts choseProduct) {
        this.choseProductsRepository.save (choseProduct);
    }

    public void deleteById(Long productId) {
        this.choseProductsRepository.deleteById (productId);
    }

    public void deleteAll() {
        this.choseProductsRepository.deleteAll ();
    }

    public ChoseProducts getByImg(String img){
        return this.choseProductsRepository.findByImg(img);
    }
}
