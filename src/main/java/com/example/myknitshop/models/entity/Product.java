package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(updatable=true,nullable = false)
    private BigDecimal price;

    @Column(updatable=true, nullable=false, unique = true, columnDefinition = "TEXT")
    private String img;

    private int quantity;

    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

}
