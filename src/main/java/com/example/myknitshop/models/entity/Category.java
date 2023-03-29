package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.myknitshop.models.enums.CategoryEnum;

@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    public Category(CategoryEnum name) {
        this.name = name;
    }

    public CategoryEnum getName() {
        return name;
    }

    public Category setName(CategoryEnum name) {
        this.name = name;
        return this;
    }
}
