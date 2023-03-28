package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.messages.MessageDTO;
import com.example.myknitshop.models.dto.viewModels.orders.OrderDetailView;
import com.example.myknitshop.models.dto.viewModels.users.AllUsersView;
import com.example.myknitshop.models.entity.*;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.MessageRepository;
import com.example.myknitshop.repository.OrderRepository;
import com.example.myknitshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private final String NEW_USERNAME = "plamena";
    private final String RAW_PASSWORD = "plamena";
    private final String ENCODED_PASSWORD = "%($)GGPPP3fdfd";

    private final Long VALID_ID = 1L;
    private final String FIRST_NAME = "Plamena";
    private final String LAST_NAME = "Plamenova";
    private final String EMAIL = "plamena@example.com";

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private MessageRepository mockMessageRepository;
    @Mock
    private OrderRepository mockOrderRepository;
    @Mock
    private ModelMapper mockMapper;
    @Mock
    private OrderService mockOrderService;
    @Mock
    private ProductService mockProductService;
    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private User user;
    @Mock
    Principal principal;
    @Mock
    Message message;
    @Spy
    @InjectMocks
    private UserService toTest;

    @Captor
    private ArgumentCaptor<Message> messageCaptor;
    @Captor
    private ArgumentCaptor<User> userCaptor;
    @Captor
    private ArgumentCaptor<Order> orderCaptor;

    private AllUsersView allUsersView;

    private Order order;
    private Product product;
    private ChoseProducts choseProduct;
    private MessageDTO messageDTO;
    private OrderDetailView orderDetailView;
    private List<Message> messages = new ArrayList<> ();
    private List<ChoseProducts> choseProducts = new ArrayList<> ();

    private Role testRole;

    @BeforeEach
    void setUp() {
        testRole = new Role ();
        testRole.setUserRole (UserRoleEnum.CLIENT);

        messages.add (message);
        choseProducts.add (choseProduct);

        user = User.builder ()
                .username (NEW_USERNAME)
                .password (mockPasswordEncoder.encode (RAW_PASSWORD))
                .firstName (FIRST_NAME)
                .lastName (LAST_NAME)
                .email (EMAIL)
                .messages (messages)
                .choseProduct (choseProducts)
                .build ();
        product = Product.builder ()
                .name ("test product")
                .price (BigDecimal.valueOf (35))
                .description ("Test product description")
                .build ();
        product.setId (VALID_ID);
        choseProduct = ChoseProducts.builder ()
                .name ("test product")
                .price (BigDecimal.valueOf (35))
                .quantity (1)
                .productSum (BigDecimal.valueOf (35))
                .build ();
        choseProduct.setId (VALID_ID);

        order = Order.builder ()
                .client (user)
                .dateOrdered (LocalDate.now ())
                .build ();

        orderDetailView = new OrderDetailView ();

        allUsersView = AllUsersView.builder ()
                .id (VALID_ID)
                .username (NEW_USERNAME)
                .email (EMAIL)
                .roles (List.of (testRole))
                .build ();
        message = Message.builder ()
                .author (user)
                .description ("Test Message Test Message Test Message")
                .build ();
        messageDTO = MessageDTO.builder ()
                .orderId (1)
                .description ("Test Message Test Message Test Message")
                .build ();

        lenient ().when (mockUserRepository.findByUsername (NEW_USERNAME)).thenReturn (Optional.of (user));
        Mockito.<Optional<User>>when (mockUserRepository.findByUsername (NEW_USERNAME)).thenReturn (Optional.of (user));
        lenient ().when (principal.getName ()).thenReturn (NEW_USERNAME);
        lenient ().when ((toTest.getUserByPrincipal (principal))).thenReturn (user);


    }




    @Test
    @WithMockUser(username = "client", roles = {"CLIENT"})
    void testAddMessage() {
        when (mockOrderService.findById (VALID_ID)).thenReturn (order);
        toTest.addMessage (messageDTO, principal);

        verify (mockMessageRepository, times (1)).save (any (Message.class));
        verify (mockUserRepository, times (1)).save (userCaptor.capture ());
        verify (mockOrderRepository, times (1)).save (orderCaptor.capture ());
    }

    @Test
    void testGetAllUsersFromRepo() {
        lenient ().when (mockUserRepository.findAll ()).thenReturn (List.of (user));
        lenient ().when (mockMapper.map (user, AllUsersView.class)).thenReturn (allUsersView);

        AllUsersView allUsersView1 = toTest.getAllUsers ().get (0);

        Assertions.assertEquals (allUsersView1.getUsername (), (allUsersView.getUsername ()));

        Assertions.assertEquals (user.getUsername (), allUsersView.getUsername ());
    }

    @Test
    void testGetOrderDetailsById() {
        lenient ().when (mockMapper.map (order, OrderDetailView.class)).thenReturn (orderDetailView);
        orderDetailView.setClientAddress ("гр.Варна ул.Тестова №3");
        orderDetailView.setClientFirstName (FIRST_NAME + " " + LAST_NAME);
        orderDetailView.setOrderSum (BigDecimal.valueOf (75));
        orderDetailView.setClientFullName (FIRST_NAME);
    }
}
