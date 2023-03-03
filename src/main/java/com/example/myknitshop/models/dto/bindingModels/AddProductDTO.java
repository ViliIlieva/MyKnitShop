package com.example.myknitshop.models.dto.bindingModels;

import com.example.myknitshop.models.entity.Category;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.CategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AddProductDTO {

    @NotBlank
    @Size(min = 5)
    private String name;

    @NotBlank
    @Size(min = 20)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private String img;

    @NotNull
    private CategoryEnum category;

}
