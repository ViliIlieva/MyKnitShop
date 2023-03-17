package com.example.myknitshop.models.dto.bindingModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDTO {
    @NotBlank
    @Size(min = 10)
    private String description;
}
