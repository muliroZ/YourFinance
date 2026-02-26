package dev.muliroz.backend.infrastructure.config;

import dev.muliroz.backend.application.user.login.LoginUseCase;
import dev.muliroz.backend.application.user.register.RegisterUserUseCase;
import dev.muliroz.backend.domain.gateway.PasswordHasher;
import dev.muliroz.backend.domain.gateway.TokenGenerator;
import dev.muliroz.backend.domain.gateway.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(
            UserRepository userRepository,
            PasswordHasher passwordHasher
    ) {
        return new RegisterUserUseCase(
                userRepository,
                passwordHasher
        );
    }

    @Bean
    public LoginUseCase loginUseCase(
            UserRepository userRepository,
            TokenGenerator tokenGenerator,
            PasswordHasher passwordHasher
    ) {
        return new LoginUseCase(
                userRepository,
                tokenGenerator,
                passwordHasher
        );
    }
}
