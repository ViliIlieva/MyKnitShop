package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.orders.MakeOrderDTO;
import com.example.myknitshop.models.entity.ChoseProducts;
import com.example.myknitshop.models.entity.PurchasedProducts;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.repository.PurchaseProductsRepository;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.service.AuthService;
import com.example.myknitshop.service.OrderService;
import com.example.myknitshop.service.PurchasedProductsService;
import com.example.myknitshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIT {
    private final String NEW_USERNAME = "test";

    @Mock
    Principal principal;
    @MockBean
    private OrderService orderService;
    @Mock
    private PurchasedProductsService purchasedProductsService;
    @Mock
    private PurchaseProductsRepository purchasedProductsRepository;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Spy
    @InjectMocks
    private UserService toTest;
    @Autowired
    private MockMvc mockMvc;
    private MakeOrderDTO makeOrderDTO;
    private User client;

    @BeforeEach
    void setUp() {
        makeOrderDTO = MakeOrderDTO.builder()
                .address("Test address")
                .phoneNumber("0899987654")
                .build();
        client = User.builder()
                .username(NEW_USERNAME)
                .password("test")
                .email("test@test.test")
                .firstName("Test")
                .lastName("Test")
                .build();
        ChoseProducts choseProduct = ChoseProducts.builder()
                .name("chose product name")
                .img("img")
                .quantity(1)
                .productSum(BigDecimal.valueOf(35))
                .price(BigDecimal.valueOf(35))
                .build();
        PurchasedProducts purchasedProduct = PurchasedProducts.builder()
                .name("chose product name")
                .img("img")
                .quantity(1)
                .productSum(BigDecimal.valueOf(35))
                .price(BigDecimal.valueOf(35))
                .build();
        userRepository.save(client);

        lenient ().when(client.getChoseProduct()).thenReturn(List.of(choseProduct));
        lenient ().when(purchasedProductsService.addProducts(List.of(purchasedProduct))).thenReturn(List.of(purchasedProduct));
        lenient().when(purchasedProductsRepository.findAll()).thenReturn(List.of(purchasedProduct));
        lenient().when(purchasedProductsRepository.save(any())).thenReturn(purchasedProduct);

        lenient ().when (userRepository.findByUsername (NEW_USERNAME)).thenReturn (Optional.of (client));
        Mockito.<Optional<User>>when (userRepository.findByUsername (NEW_USERNAME)).thenReturn (Optional.of (client));
        lenient ().when (principal.getName ()).thenReturn (NEW_USERNAME);
        lenient ().when ((toTest.getUserByPrincipal (principal))).thenReturn (client);
    }

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
    void testCartPatch() throws Exception {
        when(userService.orderProducts(makeOrderDTO, principal)).thenReturn(1L);

        mockMvc.perform(patch("/cart"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(redirectedUrl("/order/details/1"));
    }

    @Test
    @WithMockUser(username = "client", roles = {"CLIENT"})
    void testOrderByClientId() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("clientOrders"))
                .andExpect(model().attributeExists("completedOrders"))
                .andExpect(view().name("orders"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCloseOrderById() throws Exception {
        mockMvc.perform(get("/order/close/1"))
                .andExpect(status()
                        .is3xxRedirection())
                .andExpect(redirectedUrl("/user/admin"));
    }
}
