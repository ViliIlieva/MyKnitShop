package com.example.myknitshop.models.dto.viewModels;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductsViewOnHomePageDTO {
    private String name;
    private BigDecimal price;
    private String img;
}
