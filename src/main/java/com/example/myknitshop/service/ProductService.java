package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.AddProductDTO;
import com.example.myknitshop.models.dto.viewModels.products.ProductImgView;
import com.example.myknitshop.models.dto.viewModels.products.ProductWithInfoView;
import com.example.myknitshop.models.dto.viewModels.products.ProductsViewOnHomePage;
import com.example.myknitshop.models.entity.ChoseProducts;
import com.example.myknitshop.models.entity.Product;
import com.example.myknitshop.repository.CategoryRepository;
import com.example.myknitshop.repository.ChoseProductsRepository;
import com.example.myknitshop.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ChoseProductsRepository choseProductsRepository;

    public ProductService(ModelMapper modelMapper, CategoryRepository categoryRepository, ProductRepository productRepository, ChoseProductsRepository choseProductsRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.choseProductsRepository = choseProductsRepository;
    }

    //продукт има право да добавя само админа
    public boolean addProduct(AddProductDTO addProductDTO) {
        Product product = modelMapper.map(addProductDTO, Product.class);

        product.setCategory(categoryRepository.findByName(addProductDTO.getCategory()));
        this.productRepository.save(product);
        return true;
    }

    public List<ProductsViewOnHomePage> getAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(product -> {
                    return modelMapper.map(product, ProductsViewOnHomePage.class);
                }).toList();
    }

    public ProductWithInfoView getProductInfoById(Long id) {
        Product product = this.productRepository.findById (id).get ();
        return this.modelMapper.map (product, ProductWithInfoView.class);
    }

    public List<ProductImgView> getAllProductImage() {
        return this.productRepository.findAll ()
                .stream ()
                .map (product -> {
                    return modelMapper.map (product, ProductImgView.class);
                }).toList ();
    }
    public Product getProductById(Long id){
        return this.productRepository.findById(id).get();
    }

    public ChoseProducts getByImg(String img){
        return this.choseProductsRepository.findByImg(img);
    }

}
