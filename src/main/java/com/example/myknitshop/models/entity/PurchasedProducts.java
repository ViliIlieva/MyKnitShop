package com.example.myknitshop.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "purchase_products")
public class PurchasedProducts extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(updatable=true,nullable = false)
    private BigDecimal price;

    @Column(updatable=true, nullable=false, unique = true)
    @Length(max = 5000)
    private String img;

    @Column
    private int quantity;

    @Column
    private BigDecimal sum;
}
