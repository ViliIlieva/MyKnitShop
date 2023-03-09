package com.example.myknitshop.models.dto.bindingModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MakeOrderDTO {
    private Long id;

    @NotBlank
    @Size(min = 10, message = "Въведете адрес с минимум 10 символа")
    private String address;

    @NotBlank
    @Pattern(regexp="[\\d]", message = "Въведете телефонен номер")
    private String phoneNumber;
}
