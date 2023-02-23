package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @GeneratedValue
    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    @Column(nullable = false, name = "date_ordered")
    private LocalDate dateOrdered;

    @Column(nullable = false, name = "date_shipped")
    private LocalDate dateShipped;

    @ManyToOne
    private UserEntity client;

    @ManyToMany
    private Set<ProductEntity> orderedProducts;

}
