package com.example.myknitshop.repository;

import com.example.myknitshop.models.entity.PurchasedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductsRepository extends JpaRepository<PurchasedProducts, Long> {

}
