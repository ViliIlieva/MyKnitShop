package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.MakeOrderDTO;
import com.example.myknitshop.models.dto.viewModels.products.ProductViewInShoppingCard;
import com.example.myknitshop.models.entity.*;
import com.example.myknitshop.models.enums.OrderStatusEnum;
import com.example.myknitshop.repository.ChoseProductsRepository;
import com.example.myknitshop.repository.OrderRepository;
import com.example.myknitshop.repository.PurchaseProductsRepository;
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

    public UserService(ModelMapper modelMapper,
                       UserRepository userRepository,
                       ProductService productService,
                       OrderRepository orderRepository,
                       ChoseProductsRepository choseProductsRepository,
                       PurchaseProductsRepository purchaseProductsRepository, ChoseProductsService choseProductsService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.choseProductsService = choseProductsService;
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

        List<PurchasedProducts> products = client.getChoseProduct ().stream ()
                .map (p -> {
                    return modelMapper.map (p, PurchasedProducts.class);
                }).toList ();
        //TODO  this.purchaseProductsRepository.save () да съхраня новите продукти но да проверя дали вече ги има със същото количество към тях



        order.getOrderedProducts().addAll(products);
        order.setDateOrdered (LocalDate.now ());
        order.setClient (client);
        order.setOrderStatus (OrderStatusEnum.OPEN);
        order.setOrderSum (sumForAllPurchaseProduct (username));
        this.orderRepository.save (order);

        client.setAddress (makeOrderDTO.getAddress ());
        client.setPhoneNumber (makeOrderDTO.getPhoneNumber ());

        for (PurchasedProducts product : products) {
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

    public ChoseProducts mapProductToChoseProduct(Product product){
        ChoseProducts choseProducts = new ChoseProducts();
        choseProducts.setName(product.getName());
        choseProducts.setPrice(product.getPrice());
        choseProducts.setImg(product.getImg());
        return choseProducts;
    }
}
