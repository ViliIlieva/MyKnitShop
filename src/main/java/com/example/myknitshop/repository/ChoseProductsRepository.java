package com.example.myknitshop.repository;

import com.example.myknitshop.models.entity.ChoseProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoseProductsRepository extends JpaRepository<ChoseProducts, Long> {

    ChoseProducts findByImg(String img);

}
