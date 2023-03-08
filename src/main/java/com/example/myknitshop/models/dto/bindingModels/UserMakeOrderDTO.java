package com.example.myknitshop.models.dto.bindingModels;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserMakeOrderDTO {
    private Long id;

    @NotBlank
    @Size(min = 10)
    private String address;

    @NotBlank
    @Pattern(regexp="[\\d]")
    private String phoneNumber;
}
