package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.messages.MessageDTO;
import com.example.myknitshop.models.dto.viewModels.products.ProductViewInCart;
import com.example.myknitshop.models.entity.ChoseProducts;
import com.example.myknitshop.models.entity.Order;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.OrderStatusEnum;
import com.example.myknitshop.repository.ChoseProductsRepository;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.service.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIT {
    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "client", roles = {"CLIENT"})
    void testCart() throws Exception {

        mockMvc.perform(get("/cart"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("cartCashProduct"))
                .andExpect(model().attributeExists("count"))
                .andExpect(model().attributeExists("sumForAllProducts"))
                .andExpect(view().name("cart"));
    }

    @Test
    @WithMockUser(username = "client", roles = {"CLIENT"})
    void testOrderByClientId() throws Exception {
        mockMvc.perform (get ("/orders"))
                .andExpect (status().is2xxSuccessful())
                .andExpect (model ().attributeExists ("clientOrders"))
                .andExpect (model ().attributeExists ("completedOrders"))
                .andExpect(view().name("orders"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCloseOrderById() throws Exception {
    mockMvc.perform (get ("/order/close/1"))
                .andExpect (status ()
                        .is3xxRedirection ())
                .andExpect (redirectedUrl ("/user/admin"));
    }
}
