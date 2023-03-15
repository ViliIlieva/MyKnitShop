package com.example.myknitshop.models.dto.viewModels.products;

import com.example.myknitshop.models.entity.Category;
import com.example.myknitshop.models.enums.CategoryEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductToAdminPanelView {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
}
