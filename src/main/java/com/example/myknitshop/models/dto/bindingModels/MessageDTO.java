package com.example.myknitshop.models.dto.bindingModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDTO {

    private Long orderId;
    @NotBlank
    @Size(min = 10)
    private String description;
}
