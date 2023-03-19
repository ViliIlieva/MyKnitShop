package com.example.myknitshop.models.dto.bindingModels.orders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MakeOrderDTO {

    @NotBlank
    @Size(min = 10)
    private String address;

    @NotBlank
    @Pattern(regexp="08[7-9][0-9]{7}")
    private String phoneNumber;
}
