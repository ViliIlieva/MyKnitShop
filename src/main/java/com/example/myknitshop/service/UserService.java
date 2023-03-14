package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.MakeOrderDTO;
import com.example.myknitshop.models.dto.viewModels.orders.OrderDetailView;
import com.example.myknitshop.models.dto.viewModels.products.ProductViewInShoppingCard;
import com.example.myknitshop.models.entity.*;
import com.example.myknitshop.models.enums.OrderStatusEnum;
import com.example.myknitshop.repository.OrderRepository;
import com.example.myknitshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final ChoseProductsService choseProductsService;
    private final PurchasedProductsService purchasedProductsService;

    public UserService(ModelMapper modelMapper, UserRepository userRepository,
                       ProductService productService,
                       OrderRepository orderRepository,
                       ChoseProductsService choseProductsService,
                       PurchasedProductsService purchasedProductsService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.choseProductsService = choseProductsService;
        this.purchasedProductsService = purchasedProductsService;
    }

    public void addProductToChoseList(Long id, Principal principal) {
        User user = getUserByPrincipal (principal);
        Product product = this.productService.getProductById (id);
        ChoseProducts choseProduct = mapProductToChoseProduct(product);

        if(user.getChoseProduct().stream().anyMatch(p-> p.getImg().equals(product.getImg()))){
            choseProduct = user.findByImg(product.getImg());
            choseProduct.setQuantity(choseProduct.getQuantity() +1);
        }else {
            user.getChoseProduct().add(choseProduct);
            choseProduct.setQuantity(1);
        }
        choseProduct.setSum(choseProduct.getPrice ().multiply(BigDecimal.valueOf (choseProduct.getQuantity ())));
        this.choseProductsService.save(choseProduct);
        this.userRepository.save (user);
    }


    public void removeProductFromChoseList(Long productId, Principal username) {
        User user = getUserByPrincipal (username);
        user.removeProductFromChoseList(productId);
        this.choseProductsService.deleteById(productId);
        this.userRepository.save (user);
    }

    public void orderProducts(MakeOrderDTO makeOrderDTO, Principal username) {
        User client = getUserByPrincipal (username);
        Order order = new Order ();

        List<PurchasedProducts> productsToAddInDB = client.getChoseProduct ().stream ()
                .map (this::mapProductToPurchaseProduct).toList ();

       List<PurchasedProducts> productsWithId =  this.purchasedProductsService.addProducts(productsToAddInDB);

        order.getOrderedProducts().addAll(productsToAddInDB);
        order.setDateOrdered (LocalDate.now ());
        order.setClient (client);
        order.setOrderStatus (OrderStatusEnum.OPEN);
        order.setOrderSum (sumForAllPurchaseProduct (username));
        this.orderRepository.save (order);

        client.setAddress (makeOrderDTO.getAddress ());
        client.setPhoneNumber (makeOrderDTO.getPhoneNumber ());

        for (PurchasedProducts product : productsToAddInDB) {
            client.addProductToPurchaseList(product);
        }
        client.getChoseProduct().clear ();
        client.getOrders ().add (order);

        this.userRepository.save (client);
        this.choseProductsService.deleteAll ();
    }

    public Set<ProductViewInShoppingCard> getChoseListByUserToViewInShoppingCard(Principal principal) {
        User user = getUserByPrincipal (principal);

        return user.getChoseProduct().stream ()
                .map (product -> {
                    return modelMapper.map (product, ProductViewInShoppingCard.class);
                }).collect (Collectors.toSet ());
    }

    public Integer countOfItemInCart(Principal principal) {
        return getChoseListByUserToViewInShoppingCard(principal).stream ()
                .mapToInt (ProductViewInShoppingCard::getQuantity).sum ();
    }

    public BigDecimal sumForAllPurchaseProduct(Principal principal) {
        return getChoseListByUserToViewInShoppingCard(principal).stream ()
                .map (ProductViewInShoppingCard::getSum)
                .reduce (BigDecimal.ZERO, BigDecimal::add);
    }
    
    public User getUserByPrincipal(Principal principal) {
        return this.userRepository.findByUsername (principal.getName ()).get ();
    }

    public PurchasedProducts mapProductToPurchaseProduct(ChoseProducts choseProduct){
        PurchasedProducts purchasedProduct = new PurchasedProducts ();
        purchasedProduct.setName(choseProduct.getName());
        purchasedProduct.setPrice(choseProduct.getPrice());
        purchasedProduct.setImg(choseProduct.getImg());
        purchasedProduct.setQuantity (choseProduct.getQuantity ());
        return purchasedProduct;
    }

    public ChoseProducts mapProductToChoseProduct(Product product){
        ChoseProducts choseProducts = new ChoseProducts ();
        choseProducts.setName(product.getName());
        choseProducts.setPrice(product.getPrice());
        choseProducts.setImg(product.getImg());
        return choseProducts;
    }

    public OrderDetailView getOrderDetailsById(Principal principal, Long orderId) {
        User client = getUserByPrincipal(principal);
        OrderDetailView order = this.modelMapper.map(client.findOrderById(orderId), OrderDetailView.class);
        order.setClientFirstName(client.getFirstName());
        order.setClientFullName(client.getUserFullName(client.getId()));
        order.setClientAddress(client.getAddress());

        return order;
    }
}
