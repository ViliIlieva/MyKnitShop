package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.viewModels.products.ProductViewInShoppingCard;
import com.example.myknitshop.models.entity.Product;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
        User user = this.userRepository.findByUsername(principal.getName()).get();
        Product product = this.productService.getProductById(id);

        user.getPurchaseProduct().add(product);
        this.userRepository.save(user);
    }

    public List<ProductViewInShoppingCard> getPurchaseListByUser(Principal principal) {
        User user = this.userRepository.findByUsername(principal.getName()).get();

        return user.getPurchaseProduct().stream()
                .map(product -> {
                    return modelMapper.map(product, ProductViewInShoppingCard.class);
                }).toList();
    }
}
