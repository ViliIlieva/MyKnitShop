package com.example.myknitshop.models.dto.viewModels.products;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductWithInfoView {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String img;
}
