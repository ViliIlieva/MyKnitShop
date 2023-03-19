package com.example.myknitshop.models.dto.bindingModels;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDTO {

    @NotNull
    @Positive
    private Integer orderId;

    @NotBlank
    @Size(min = 10)
    private String description;
}
