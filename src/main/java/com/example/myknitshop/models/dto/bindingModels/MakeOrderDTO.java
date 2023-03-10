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
    @Size(min = 10)
    private String address;

    @NotBlank
    @Pattern(regexp="08[7-9][0-9]{7}")
    private String phoneNumber;
}
