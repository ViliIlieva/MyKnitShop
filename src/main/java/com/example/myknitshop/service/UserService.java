package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.viewModels.products.ProductViewInShoppingCard;
import com.example.myknitshop.models.entity.Product;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductService productService;

    public UserService(ModelMapper modelMapper,
                       UserRepository userRepository,
                       ProductService productService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    public void addProductToBuyList(Long id, Principal principal) {
        User user = getUsernameByPrincipal (principal);
        Product product = this.productService.getProductById(id);

        if(user.getPurchaseProduct ().contains (product)){
            product.setQuantity (product.getQuantity () + 1);
        }
        product.setSum (product.getPrice ().multiply (BigDecimal.valueOf (product.getQuantity ())));

        user.getPurchaseProduct().add(product);
        this.userRepository.save(user);
    }

    public void removeProduct(Long productId, Principal username) {
        User user = getUsernameByPrincipal (username);
        user.removeProductFromPurchaseList(productId);

        Product product = this.productService.getProductById(productId);
        product.setQuantity(1);
        product.setSum(null);

        this.userRepository.save(user);
    }

    public List<ProductViewInShoppingCard> getPurchaseListByUser(Principal principal) {
        User user = getUsernameByPrincipal (principal);

        return user.getPurchaseProduct().stream()
                .map(product -> {
                    return modelMapper.map(product, ProductViewInShoppingCard.class);
                }).toList();
    }

    public Integer countOfItemInShopCard (Principal principal){
        return getPurchaseListByUser (principal).stream ()
                .mapToInt (ProductViewInShoppingCard::getQuantity).sum ();
    }

    public BigDecimal sumForAllPurchaseProduct(Principal principal){
        BigDecimal sum = BigDecimal.ZERO;
        return  getPurchaseListByUser (principal).stream ()
                .map (ProductViewInShoppingCard::getSum)
                .reduce (BigDecimal.ZERO, BigDecimal::add);
    }

    public User getUsernameByPrincipal(Principal principal){
        return this.userRepository.findByUsername(principal.getName()).get();
    }


}
