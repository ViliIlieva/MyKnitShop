package com.example.myknitshop.models.dto.viewModels.orders;

import com.example.myknitshop.models.entity.Message;
import com.example.myknitshop.models.entity.PurchasedProducts;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.OrderStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class OpenOrderToAdminPanelView {
    private Long id;
    private LocalDate dateOrdered;
    private BigDecimal orderSum;
    private User client;
    private OrderStatusEnum orderStatus;
    private List<PurchasedProducts> orderedProducts;
}
