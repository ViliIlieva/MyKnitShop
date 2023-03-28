package com.example.myknitshop.service;

import com.example.myknitshop.models.entity.ChoseProducts;
import com.example.myknitshop.repository.ChoseProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ChoseProductsServiceTest {
    @Mock
    private ChoseProductsRepository choseProductsRepository;
    private ChoseProductsService toTest;
    private ChoseProducts choseProduct;

    @Captor
    private ArgumentCaptor<ChoseProducts> choseProductCaptor;

    @BeforeEach
    void setUp(){
        toTest = new ChoseProductsService(choseProductsRepository);

        choseProduct = new ChoseProducts();
        choseProduct.setName("test product");
        choseProduct.setPrice(BigDecimal.valueOf(35));
        choseProduct.setQuantity(1);
    }

    @Test
    void testSave(){
        toTest.save(choseProduct);

        verify(choseProductsRepository, times(1))
                .save(choseProductCaptor.capture());
        ChoseProducts argument = choseProductCaptor.getValue();
        Assertions.assertEquals(choseProduct.getQuantity(), argument.getQuantity());
        Assertions.assertEquals(choseProduct.getName(), argument.getName());
        Assertions.assertEquals(choseProduct.getPrice(), argument.getPrice());
    }

    @Test
    void testDeleteById(){
        toTest.deleteById(1L);
        verify(choseProductsRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void testDeleteAll(){
        toTest.deleteAll();
        verify(choseProductsRepository, times(1))
                .deleteAll();
    }

    @Test
    void testGetByImg(){
        toTest.getByImg("img");
        verify(choseProductsRepository, times(1))
                .findByImg("img");
    }

}
