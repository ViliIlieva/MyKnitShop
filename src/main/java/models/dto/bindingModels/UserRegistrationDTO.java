package models.dto.bindingModels;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationDTO {

    @NotNull
    @Size(min = 5, max = 20)
    private String username;

    @NotNull
    @Size(min = 5, max = 50)
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @NotNull
    @Size(min = 3, max = 20)
    private String confirmPassword;
}

