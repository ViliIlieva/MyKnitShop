package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.products.AddProductDTO;
import com.example.myknitshop.models.dto.viewModels.products.ProductImgView;
import com.example.myknitshop.models.dto.viewModels.products.ProductToAdminPanelView;
import com.example.myknitshop.models.dto.viewModels.products.ProductWithInfoView;
import com.example.myknitshop.models.dto.viewModels.products.ProductsViewOnHomePage;
import com.example.myknitshop.models.entity.Product;
import com.example.myknitshop.repository.CategoryRepository;
import com.example.myknitshop.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ImageCloudService imageCloudService;

    @Autowired
    public ProductService(ModelMapper modelMapper, CategoryRepository categoryRepository,
                          ProductRepository productRepository, ImageCloudService imageCloudService) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.imageCloudService = imageCloudService;
    }

    public boolean addProduct(AddProductDTO addProductDTO) {
        String pictureUrl = imageCloudService.saveImage(addProductDTO.getImg());
        Product product = modelMapper.map(addProductDTO, Product.class);

        product.setCategory(categoryRepository.findById (Long.parseLong (addProductDTO.getCategory()))
                .orElseThrow(() -> new Error("Category not found!")));
        product.setImg(pictureUrl);
        this.productRepository.save(product);
        return true;
    }

//    public boolean addProduct(AddProductDTO addProductDTO) {
//        Product product = modelMapper.map(addProductDTO, Product.class);
//
//        product.setCategory(categoryRepository.findById (Long.parseLong (addProductDTO.getCategory()))
//                .orElseThrow(() -> new Error("Category not found!")));
//        this.productRepository.save(product);
//        return true;
//    }

    public List<ProductsViewOnHomePage> getAllProductsToViewOnHomePage() {
        return this.productRepository.findAll()
                .stream()
                .map(product -> {
                    return modelMapper.map(product, ProductsViewOnHomePage.class);
                }).toList();
    }

    public ProductWithInfoView getProductInfoById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new Error("Product not found!"));
        return this.modelMapper.map(product, ProductWithInfoView.class);
    }

    public List<ProductImgView> getAllProductImage() {
        return this.productRepository.findAll()
                .stream()
                .map(product -> {
                    return modelMapper.map(product, ProductImgView.class);
                }).toList();
    }

    public Product getProductById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new Error("Product not found!"));
    }

    public List<ProductToAdminPanelView> getAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(p -> {
                    return modelMapper.map(p, ProductToAdminPanelView.class);
                }).toList();

    }

    public void deleteProductById(Long productId) {
        this.productRepository.deleteById(productId);
    }

    public void editProduct(Long productId, ProductWithInfoView editProductDTO) {
        Product productToEdit = this.productRepository.findById(productId)
                .orElseThrow(() -> new Error("Product not found!"));
        productToEdit.setDescription(editProductDTO.getDescription());
        productToEdit.setPrice(editProductDTO.getPrice());
        productToEdit.setImg(editProductDTO.getImg());
        this.productRepository.save(productToEdit);
    }
}
