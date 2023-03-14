package com.example.myknitshop.models.dto.viewModels.orders;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderDetailView {
    private Long id;
    private String clientFirstName;
    private String clientFullName;
    private String clientAddress;
    private BigDecimal orderSum;
}
