package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.orders.MakeOrderDTO;
import com.example.myknitshop.models.dto.viewModels.orders.OrderDetailView;
import com.example.myknitshop.models.dto.viewModels.products.ProductViewInCart;
import com.example.myknitshop.models.entity.*;
import com.example.myknitshop.models.enums.CategoryEnum;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIT {
    private final String NEW_USERNAME = "test";

    @Mock
    Principal principal;
    @Mock
    private ModelMapper mockMapper;
    @MockBean
    private OrderService orderService;
    @Mock
    private PurchasedProductsService purchasedProductsService;
    @Mock
    private PurchaseProductsRepository purchasedProductsRepository;
    @Mock
    private UserRepository userRepository;
    @Spy
    @InjectMocks
    private UserService toTest;
    @Autowired
    private MockMvc mockMvc;
    private MakeOrderDTO makeOrderDTO;
    private ChoseProducts choseProduct;
    private ProductViewInCart productViewInCart;
    private PurchasedProducts purchasedProduct;
    private Category category;
    private Product product;
    private User client;
    List<ChoseProducts> choseProducts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .name(CategoryEnum.HAT)
                .build();
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
                .choseProduct(choseProducts)
                .build();
        product = Product.builder()
                .name("chose product name")
                .price(BigDecimal.valueOf(35))
                .description("Test description Test description")
                .category(category)
                .build();
        choseProduct = ChoseProducts.builder()
                .name("chose product name")
                .img("img")
                .quantity(1)
                .productSum(BigDecimal.valueOf(35))
                .price(BigDecimal.valueOf(35))
                .build();
        choseProducts.add(choseProduct);
        productViewInCart = ProductViewInCart.builder()
                .name("chose product name")
                .img("img")
                .quantity(1)
                .productSum(BigDecimal.valueOf(35))
                .price(BigDecimal.valueOf(35))
                .build();
        purchasedProduct = PurchasedProducts.builder()
                .name("chose product name")
                .img("img")
                .quantity(1)
                .productSum(BigDecimal.valueOf(35))
                .price(BigDecimal.valueOf(35))
                .build();
        userRepository.save(client);

        lenient().when(client.getChoseProduct()).thenReturn(List.of(choseProduct));
        lenient().when(purchasedProductsService.addProducts(List.of(purchasedProduct))).thenReturn(List.of(purchasedProduct));
        lenient().when(purchasedProductsRepository.findAll()).thenReturn(List.of(purchasedProduct));
        lenient().when(purchasedProductsRepository.save(any())).thenReturn(purchasedProduct);
        lenient().when(mockMapper.map(product, ProductViewInCart.class)).thenReturn(productViewInCart);

        lenient().when(userRepository.findByUsername(NEW_USERNAME)).thenReturn(Optional.of(client));
        Mockito.<Optional<User>>when(userRepository.findByUsername(NEW_USERNAME)).thenReturn(Optional.of(client));
        lenient().when(principal.getName()).thenReturn(NEW_USERNAME);
        lenient().when((toTest.getUserByPrincipal(principal))).thenReturn(client);
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
        when(toTest.orderProducts(makeOrderDTO, principal)).thenReturn(1L);
        when((toTest.getChoseListByUserToViewInShoppingCard(principal))).thenReturn(Set.of(productViewInCart));
        when(toTest.sumForAllPurchaseProduct(principal)).thenReturn(BigDecimal.valueOf(35));

        mockMvc.perform(patch("/cart").with(csrf()))
                .andExpect(status().is3xxRedirection())
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
