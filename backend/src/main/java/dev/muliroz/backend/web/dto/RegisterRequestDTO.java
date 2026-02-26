package dev.muliroz.backend.web.dto;

import dev.muliroz.backend.application.user.register.RegisterUserInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO (
        @NotBlank(message = "Name field is mandatory")
        @Size(min = 2, max = 50, message = "Name field cannot exceed 50 characters")
        String name,

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email field is mandatory")
        @Size(max = 100, message = "Email field cannot exceed 100 characters")
        String email,

        @NotBlank(message = "Password field is mandatory")
        @Size(min = 6, message = "Password field needs at least 6 characters")
        String password
) {
    public static RegisterUserInput toInput(RegisterRequestDTO dto) {
        return new RegisterUserInput(
                dto.name,
                dto.email,
                dto.password
        );
    }
}
