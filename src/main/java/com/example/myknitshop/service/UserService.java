package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.orders.MakeOrderDTO;
import com.example.myknitshop.models.dto.bindingModels.messages.MessageDTO;
import com.example.myknitshop.models.dto.viewModels.orders.CompleteOrdersIdView;
import com.example.myknitshop.models.dto.viewModels.orders.OrderDetailView;
import com.example.myknitshop.models.dto.viewModels.orders.UserOrdersView;
import com.example.myknitshop.models.dto.viewModels.products.ProductViewInCart;
import com.example.myknitshop.models.dto.viewModels.users.AllUsersView;
import com.example.myknitshop.models.entity.*;
import com.example.myknitshop.models.enums.OrderStatusEnum;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.MessageRepository;
import com.example.myknitshop.repository.OrderRepository;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final MessageRepository messageRepository;
    private final OrderService orderService;
    private final ProductService productService;
    private final ChoseProductsService choseProductsService;
    private final PurchasedProductsService purchasedProductsService;
    private final UserRoleRepository userRoleRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository,
                       ProductService productService,
                       OrderRepository orderRepository,
                       MessageRepository messageRepository, OrderService orderService, ChoseProductsService choseProductsService,
                       PurchasedProductsService purchasedProductsService, UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.messageRepository = messageRepository;
        this.orderService = orderService;
        this.choseProductsService = choseProductsService;
        this.purchasedProductsService = purchasedProductsService;
        this.userRoleRepository = userRoleRepository;
    }

    public void addProductToChoseList(Long id, Principal principal) {
        User user = getUserByPrincipal (principal);
        Product product = this.productService.getProductById (id);
        ChoseProducts choseProduct = mapProductToChoseProduct (product);

        if (user.getChoseProduct ().stream ().anyMatch (p -> p.getImg ().equals (product.getImg ()))) {
            choseProduct = user.findByImg (product.getImg ());
            choseProduct.setQuantity (choseProduct.getQuantity () + 1);
        } else {
            user.getChoseProduct ().add (choseProduct);
            choseProduct.setQuantity (1);
        }
        choseProduct.setSum (choseProduct.getPrice ().multiply (BigDecimal.valueOf (choseProduct.getQuantity ())));
        this.choseProductsService.save (choseProduct);
        this.userRepository.save (user);
    }


    public void removeProductFromChoseList(Long productId, Principal username) {
        User user = getUserByPrincipal (username);
        user.removeProductFromChoseList (productId);
        this.choseProductsService.deleteById (productId);
        this.userRepository.save (user);
    }

    public Long orderProducts(MakeOrderDTO makeOrderDTO, Principal username) {
        User client = getUserByPrincipal (username);
        Order order = new Order ();

        List<PurchasedProducts> productsToAddInDB = client.getChoseProduct ().stream ()
                .map (this::mapProductToPurchaseProduct).toList ();

        List<PurchasedProducts> productsWithId = this.purchasedProductsService.addProducts (productsToAddInDB);

        order.getOrderedProducts ().addAll (productsToAddInDB);
        order.setDateOrdered (LocalDate.now ());
        order.setClient (client);
        order.setOrderStatus (OrderStatusEnum.OPEN);
        order.setOrderSum (sumForAllPurchaseProduct (username));
        this.orderRepository.save (order);

        client.setAddress (makeOrderDTO.getAddress ());
        client.setPhoneNumber (makeOrderDTO.getPhoneNumber ());

        for (PurchasedProducts product : productsToAddInDB) {
            client.addProductToPurchaseList (product);
        }
        client.getChoseProduct ().clear ();
        client.getOrders ().add (order);

        this.userRepository.save (client);
        this.choseProductsService.deleteAll ();
        return order.getId ();
    }

    public Set<ProductViewInCart> getChoseListByUserToViewInShoppingCard(Principal principal) {
        User user = getUserByPrincipal (principal);

        return user.getChoseProduct ().stream ()
                .map (product -> {
                    return modelMapper.map (product, ProductViewInCart.class);
                }).collect (Collectors.toSet ());
    }

    public Integer countOfItemInCart(Principal principal) {
        return getChoseListByUserToViewInShoppingCard (principal).stream ()
                .mapToInt (ProductViewInCart::getQuantity).sum ();
    }

    public BigDecimal sumForAllPurchaseProduct(Principal principal) {
        return getChoseListByUserToViewInShoppingCard (principal).stream ()
                .map (ProductViewInCart::getSum)
                .reduce (BigDecimal.ZERO, BigDecimal::add);
    }

    public User getUserByPrincipal(Principal principal) {
        return this.userRepository.findByUsername (principal.getName ()).get ();
    }

    public PurchasedProducts mapProductToPurchaseProduct(ChoseProducts choseProduct) {
        PurchasedProducts purchasedProduct = new PurchasedProducts ();
        purchasedProduct.setName (choseProduct.getName ());
        purchasedProduct.setPrice (choseProduct.getPrice ());
        purchasedProduct.setImg (choseProduct.getImg ());
        purchasedProduct.setQuantity (choseProduct.getQuantity ());
        return purchasedProduct;
    }

    public ChoseProducts mapProductToChoseProduct(Product product) {
        ChoseProducts choseProducts = new ChoseProducts ();
        choseProducts.setName (product.getName ());
        choseProducts.setPrice (product.getPrice ());
        choseProducts.setImg (product.getImg ());
        return choseProducts;
    }

    public OrderDetailView getOrderDetailsById(Principal principal, Long orderId) {
        User client = getUserByPrincipal (principal);
        OrderDetailView order = this.modelMapper.map (client.findOrderById (orderId), OrderDetailView.class);
        order.setClientFirstName (client.getFirstName ());
        order.setClientFullName (client.getUserFullName (client.getId ()));
        order.setClientAddress (client.getAddress ());
        return order;
    }

    public List<UserOrdersView> getAllOrders(Principal principal) {
        User client = getUserByPrincipal (principal);
        List<UserOrdersView> orders = client.getOrders ().stream ().map (o -> {
                    return modelMapper.map (o, UserOrdersView.class);
                }).toList ()
                .stream ().sorted ((o2, o1) -> o1.getOrderStatus ().compareTo (o2.getOrderStatus ()))
                .collect (Collectors.toList ());
        return orders;
    }

    public List<CompleteOrdersIdView> getCompletedOrdersWithoutMessage(Principal principal) {
        return getAllOrders (principal)
                .stream ()
                .filter (o -> o.getOrderStatus ().equals (OrderStatusEnum.COMPLETED))
                .filter (o -> o.getMessage () == null)
                .map (ord -> {
                    return modelMapper.map (ord, CompleteOrdersIdView.class);
                }).toList ();
    }

    public void addMessage(MessageDTO messageDTO, Principal principal) {
        User client = getUserByPrincipal (principal);
        Order order = this.orderService.findById (Long.valueOf (messageDTO.getOrderId ()));

        Message message = new Message ();
        message.setAuthor (client);
        message.setDescription (messageDTO.getDescription ());
        this.messageRepository.save (message);

        client.getMessages ().add (message);
        this.userRepository.save (client);

        order.setMessage (message);
        this.orderRepository.save (order);
    }

    public List<AllUsersView> getAllUsers() {
        return this.userRepository.findAll ()
                .stream ()
                .map (u -> {
                    return modelMapper.map (u, AllUsersView.class);
                })
                .toList ();
    }

    public void changeUserRole(Long userId) {
        User user = this.userRepository.findById (userId).get ();
        UserRoleEnum userRole = user.getUserRoles ().stream ().findFirst ().get ().getUserRole ();
        if (userRole.equals (UserRoleEnum.ADMIN)) {
            user.getUserRoles ().clear ();
            user.setUserRoles (userRoleRepository.findByUserRole (UserRoleEnum.CLIENT));
        } else {
            user.getUserRoles ().clear ();
            user.setUserRoles (userRoleRepository.findByUserRole (UserRoleEnum.ADMIN));
        }
        this.userRepository.save (user);
    }

    public User findByUsername(String username) {
       return this.userRepository.findByUsername (username).get ();
    }

    public void save(User user) {
        this.userRepository.save (user);
    }
}
