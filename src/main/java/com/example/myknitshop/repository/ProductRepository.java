package com.example.myknitshop.repository;

import com.example.myknitshop.models.dto.viewModels.products.ProductImgDTO;
import com.example.myknitshop.models.entity.Category;
import com.example.myknitshop.models.entity.Product;
import com.example.myknitshop.models.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
