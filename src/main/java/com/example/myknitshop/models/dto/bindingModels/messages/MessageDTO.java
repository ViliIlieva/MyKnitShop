package com.example.myknitshop.models.dto.bindingModels.messages;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    @NotNull
    @Positive
    private Integer orderId;

    @NotBlank
    @Size(min = 10)
    private String description;
}
