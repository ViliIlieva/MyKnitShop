package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(updatable=true, nullable=false)
    private String photo;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne
    private UserEntity buyer;

}
