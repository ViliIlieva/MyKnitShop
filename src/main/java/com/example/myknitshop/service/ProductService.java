package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.AddProductDTO;
import com.example.myknitshop.models.dto.viewModels.ProductsViewOnHomePageDTO;
import com.example.myknitshop.models.entity.Product;
import com.example.myknitshop.repository.CategoryRepository;
import com.example.myknitshop.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductService(ModelMapper modelMapper, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    //продукт има право да добавя само админа
    public boolean addProduct(AddProductDTO addProductDTO) {
        Product product = modelMapper.map(addProductDTO, Product.class);

        product.setCategory(categoryRepository.findByName(addProductDTO.getCategory()));
        this.productRepository.save(product);
        return true;
    }

    public List<ProductsViewOnHomePageDTO> getAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(product -> {
                    ProductsViewOnHomePageDTO products = modelMapper.map(product, ProductsViewOnHomePageDTO.class);
                    return  products;
                }).toList();
    }
}
