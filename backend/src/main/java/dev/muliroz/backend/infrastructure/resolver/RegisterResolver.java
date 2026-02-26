package dev.muliroz.backend.infrastructure.resolver;

import dev.muliroz.backend.application.user.register.RegisterUserInput;
import dev.muliroz.backend.application.user.register.RegisterUserUseCase;
import dev.muliroz.backend.web.dto.RegisterRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class RegisterResolver {

    private final RegisterUserUseCase registerUserUseCase;

    public RegisterResolver(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    public void register(RegisterRequestDTO dto) {
        RegisterUserInput input = RegisterRequestDTO.toInput(dto);
        registerUserUseCase.execute(input);
    }
}
