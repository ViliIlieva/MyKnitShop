package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.MakeOrderDTO;
import com.example.myknitshop.models.dto.viewModels.products.ProductViewInShoppingCard;
import com.example.myknitshop.models.entity.Order;
import com.example.myknitshop.models.entity.Product;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.OrderStatusEnum;
import com.example.myknitshop.repository.OrderRepository;
import com.example.myknitshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    public UserService(ModelMapper modelMapper,
                       UserRepository userRepository,
                       ProductService productService,
                       OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    public void addProductToBuyList(Long id, Principal principal) {
        User user = getUserByPrincipal (principal);
        Product product = this.productService.getProductById(id);

        if(user.getPurchaseProduct ().contains (product)){
            product.setQuantity (product.getQuantity () + 1);
        }else {
            user.getPurchaseProduct().add(product);
        }
        product.setSum (product.getPrice ().multiply (BigDecimal.valueOf (product.getQuantity ())));
        this.userRepository.save(user);
    }

    public void removeProduct(Long productId, Principal username) {
        User user = getUserByPrincipal (username);
        user.removeProductFromPurchaseList(productId);

        Product product = this.productService.getProductById(productId);
        product.setQuantity(1);
        product.setSum(null);

        this.userRepository.save(user);
    }

    public Set<ProductViewInShoppingCard> getPurchaseListByUserToViewInShoppingCard(Principal principal) {
        User user = getUserByPrincipal (principal);

        return user.getPurchaseProduct().stream()
                .map(product -> {
                    return modelMapper.map(product, ProductViewInShoppingCard.class);
                }).collect(Collectors.toSet());
    }

    public Integer countOfItemInCart(Principal principal){
        return getPurchaseListByUserToViewInShoppingCard (principal).stream ()
                .mapToInt (ProductViewInShoppingCard::getQuantity).sum ();
    }

    public BigDecimal sumForAllPurchaseProduct(Principal principal){
        return  getPurchaseListByUserToViewInShoppingCard (principal).stream ()
                .map (ProductViewInShoppingCard::getSum)
                .reduce (BigDecimal.ZERO, BigDecimal::add);
    }

    public void orderProducts(MakeOrderDTO makeOrderDTO, Principal username) {
        User client = getUserByPrincipal (username);
        Order order = new Order ();

        order.getOrderedProducts ().addAll (client.getPurchaseProduct ());
        order.setDateOrdered (LocalDate.now ());
        order.setClient (client);
        order.setOrderStatus (OrderStatusEnum.OPEN);
        order.setOrderSum (sumForAllPurchaseProduct (username));
        this.orderRepository.save (order);


        client.setAddress (makeOrderDTO.getAddress ());
        client.setPhoneNumber (makeOrderDTO.getPhoneNumber ());
        client.getAllBuyProduct ().addAll (client.getPurchaseProduct ());
        client.getPurchaseProduct ().clear ();
        client.getOrders ().add (order);
        this.userRepository.save(client);



    }


//    public Set<Product> getPurchaseListByClient(Principal principal) {
//        User client = getUserByPrincipal (principal);
//        return client.getPurchaseProduct();
//    }

//    public void buyProduct(Principal username, MakeOrderDTO makeOrderDTO) {
//        User client = getUserByPrincipal (username);
//
//        client.setAddress (makeOrderDTO.getAddress ());
//        client.setPhoneNumber (makeOrderDTO.getPhoneNumber ());
//        client.getAllBuyProduct ().addAll (client.getPurchaseProduct ());
//        client.getPurchaseProduct ().clear ();
//        this.userRepository.save(client);
//    }

    public User getUserByPrincipal(Principal principal){
        return this.userRepository.findByUsername(principal.getName()).get();
    }
}
