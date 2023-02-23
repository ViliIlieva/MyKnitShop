package com.example.myknitshop.repository;

import com.example.myknitshop.models.entity.CategoryEntity;
import com.example.myknitshop.models.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(CategoryEnum name);
}
