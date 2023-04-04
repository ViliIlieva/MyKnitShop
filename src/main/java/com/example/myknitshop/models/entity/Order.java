package com.example.myknitshop.models.entity;

import com.example.myknitshop.models.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_orders",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "purchase_product_id",
                    referencedColumnName = "id"))
    private List<PurchasedProducts> orderedProducts = new ArrayList<> ();

    @OneToOne
    private Message message;

}
