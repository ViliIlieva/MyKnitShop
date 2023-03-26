package com.example.myknitshop.models.dto.viewModels.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailView {
    private Long id;
    private String clientFirstName;
    private String clientFullName;
    private String clientAddress;
    private BigDecimal orderSum;
}
