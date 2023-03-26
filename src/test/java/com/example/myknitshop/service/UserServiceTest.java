package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.viewModels.users.AllUsersView;
import com.example.myknitshop.models.entity.Role;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.MessageRepository;
import com.example.myknitshop.repository.OrderRepository;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.repository.UserRoleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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

    private User user;

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

        user = new User ()
                .setUsername (NEW_USERNAME)
                .setPassword (mockPasswordEncoder.encode (RAW_PASSWORD))
                .setFirstName (FIRST_NAME)
                .setLastName (LAST_NAME)
                .setUserRoles (List.of (testRole))
                .setEmail (EMAIL);
        allUsersView = AllUsersView.builder()
                .id (VALID_ID)
                .username (NEW_USERNAME)
                .email (EMAIL)
                .roles (List.of (testRole))
                .build();

    }
    @Test
    void testGetAllUsersFromRepo() {
        lenient().when(mockUserRepository.findAll()).thenReturn(List.of(user));
        lenient().when(mockMapper.map(user, AllUsersView.class)).thenReturn(allUsersView);

        AllUsersView allUsersView1 = toTest.getAllUsers ().get (0);

        Assertions.assertEquals (allUsersView1.getUsername (), (allUsersView.getUsername ()));

        Assertions.assertEquals(user.getUsername (), allUsersView.getUsername ());
    }


}
