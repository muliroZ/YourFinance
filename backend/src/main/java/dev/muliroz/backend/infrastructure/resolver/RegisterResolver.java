package dev.muliroz.backend.infrastructure.resolver;

import dev.muliroz.backend.application.user.register.RegisterUserInput;
import dev.muliroz.backend.application.user.register.RegisterUserUseCase;
import dev.muliroz.backend.web.dto.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class RegisterResolver {

    private final RegisterUserUseCase registerUserUseCase;

    public RegisterResolver(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    public void register(RegisterRequest dto) {
        RegisterUserInput input = RegisterRequest.toInput(dto);
        registerUserUseCase.execute(input);
    }
}
