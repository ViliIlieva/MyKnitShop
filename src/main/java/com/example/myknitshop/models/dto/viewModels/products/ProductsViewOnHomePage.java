package com.example.myknitshop.models.dto.viewModels.products;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductsViewOnHomePage {
    private Long id;
    private String name;
    private BigDecimal price;
    private String img;
}
