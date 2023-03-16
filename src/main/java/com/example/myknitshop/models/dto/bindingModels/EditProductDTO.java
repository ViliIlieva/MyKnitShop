package com.example.myknitshop.models.dto.bindingModels;

import com.example.myknitshop.models.enums.CategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class EditProductDTO {
    private Long id;
    @Size(min = 20)
    private String description;

    @Positive
    private BigDecimal price;

    private String img;
}
