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
public class Order extends BaseEntity {

    @GeneratedValue
    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    @Column(nullable = false, name = "date_ordered")
    private LocalDate dateOrdered;

    @ManyToOne
    private User client;

    @ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_orders",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Product> orderedProducts;
}
