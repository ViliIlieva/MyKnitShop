package com.example.myknitshop.models.entity;

import com.example.myknitshop.models.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false, name = "date_ordered")
    private LocalDate dateOrdered;

    @Column(name = "order_sum")
    private BigDecimal orderSum;

    @ManyToOne
    private User client;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatusEnum orderStatus;

    @ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_orders",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"))
    private List<Product> orderedProducts;
}
