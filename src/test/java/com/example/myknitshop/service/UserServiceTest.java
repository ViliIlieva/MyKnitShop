package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.messages.MessageDTO;
import com.example.myknitshop.models.dto.viewModels.orders.OrderDetailView;
import com.example.myknitshop.models.dto.viewModels.users.AllUsersView;
import com.example.myknitshop.models.entity.Message;
import com.example.myknitshop.models.entity.Order;
import com.example.myknitshop.models.entity.Role;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.MessageRepository;
import com.example.myknitshop.repository.OrderRepository;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private final String NEW_USERNAME = "plamena";
    private final String RAW_PASSWORD = "plamena";
    private final String ENCODED_PASSWORD = "%($)GGPPP3fdfd";

    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = 300L;

    private final String FIRST_NAME = "Plamena";
    private final String LAST_NAME = "Plamenova";
    private final String EMAIL = "plamena@example.com";
    private final String NON_EXISTING_USERNAME = "plami";

    private UserService toTest;

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private ModelMapper mockMapper;
    @Mock
    private OrderRepository mockOrderRepository;
    @Mock
    private MessageRepository mockMessageRepository;
    @Mock
    private OrderService mockOrderService;
    @Mock
    private ProductService mockProductService;
    @Mock
    private ChoseProductsService mockChoseProductsService;
    @Mock
    private PurchasedProductsService mockPurchasedProductsService;
    @Mock
    private UserRoleRepository mockUserRoleRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    private AllUsersView allUsersView;

    private User testUser;
    private Order testOrder;
    private OrderDetailView testOrderDetailView;

    private Role testRole;

    @BeforeEach
    void setUp() {
        toTest = new UserService (
                mockMapper, mockUserRepository,
                mockProductService, mockOrderRepository,
                mockMessageRepository, mockOrderService,
                mockChoseProductsService, mockPurchasedProductsService,
                mockUserRoleRepository

        );

        testRole = new Role ();
        testRole.setUserRole (UserRoleEnum.CLIENT);

        testUser = new User ()
                .setUsername (NEW_USERNAME)
                .setPassword (mockPasswordEncoder.encode (RAW_PASSWORD))
                .setFirstName (FIRST_NAME)
                .setLastName (LAST_NAME)
                .setEmail (EMAIL);

        testOrder = Order.builder ()
                .client (testUser)
                .dateOrdered (LocalDate.now ())
                .build ();

        testOrderDetailView = new OrderDetailView ();

        allUsersView = AllUsersView.builder ()
                .id (VALID_ID)
                .username (NEW_USERNAME)
                .email (EMAIL)
                .roles (List.of (testRole))
                .build ();

    }

    @Test
    void testGetAllUsersFromRepo() {
        lenient ().when (mockUserRepository.findAll ()).thenReturn (List.of (testUser));
        lenient ().when (mockMapper.map (testUser, AllUsersView.class)).thenReturn (allUsersView);

        AllUsersView allUsersView1 = toTest.getAllUsers ().get (0);

        Assertions.assertEquals (allUsersView1.getUsername (), (allUsersView.getUsername ()));

        Assertions.assertEquals (testUser.getUsername (), allUsersView.getUsername ());
    }

    @Test
    void testGetOrderDetailsById(){
        lenient ().when (mockMapper.map (testOrder, OrderDetailView.class)).thenReturn (testOrderDetailView);
        testOrderDetailView.setClientAddress ("гр.Варна ул.Тестова №3");
        testOrderDetailView.setClientFirstName (FIRST_NAME + " " + LAST_NAME);
        testOrderDetailView.setOrderSum (BigDecimal.valueOf (75));
        testOrderDetailView.setClientFullName (FIRST_NAME);

//        OrderDetailView orderDetailView = toTest.getOrderDetailsById ((Principal) testUser, VALID_ID);
    }
}
