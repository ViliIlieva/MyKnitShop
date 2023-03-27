package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.viewModels.products.ProductViewInCart;
import com.example.myknitshop.models.entity.ChoseProducts;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.repository.ChoseProductsRepository;
import com.example.myknitshop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIT {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ChoseProductsRepository choseProductsRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "client", roles = {"CLIENT"})
    void testCart() throws Exception {
        ProductViewInCart productViewInCart = ProductViewInCart.builder()
                .id(1L)
                .price(BigDecimal.valueOf(35))
                .name("testProduct")
                .productSum (BigDecimal.valueOf(35))
                .quantity(1)
                .build();
        ChoseProducts choseProducts = ChoseProducts.builder()
                .name("testProduct")
                .price(BigDecimal.valueOf(35))
                .quantity(1)
                .sum(BigDecimal.valueOf(35))
                .build();
        choseProductsRepository.save(choseProducts);

        User client = User.builder()
                .username("client")
                .password("client")
                .firstName("Client")
                .lastName("Client")
                .choseProduct(List.of(choseProducts))
                .email("x@test")
                .build();
        userRepository.save(client);

        mockMvc.perform(get("/cart"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("cartCashProduct"))
                .andExpect(model().attributeExists("count"))
                .andExpect(model().attributeExists("sumForAllProducts"))
                .andExpect(view().name("cart"));
    }
}
