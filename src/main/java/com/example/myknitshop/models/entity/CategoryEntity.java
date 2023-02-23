package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.myknitshop.models.enums.CategoryEnum;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    @Column(name = "needed_day", nullable = false)
    private Integer neededDay;

    public CategoryEntity(CategoryEnum name) {
        this.name = name;
    }
}
