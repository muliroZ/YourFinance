package dev.muliroz.backend.application.user.login;

import dev.muliroz.backend.domain.entity.User;
import dev.muliroz.backend.domain.gateway.PasswordHasher;
import dev.muliroz.backend.domain.gateway.TokenGenerator;
import dev.muliroz.backend.domain.gateway.UserRepository;

public class LoginUseCase {

    private final UserRepository repository;
    private final TokenGenerator tokenGenerator;
    private final PasswordHasher hasher;

    public LoginUseCase(UserRepository repository, TokenGenerator generator, PasswordHasher hasher) {
        this.repository = repository;
        this.tokenGenerator = generator;
        this.hasher = hasher;
    }

    public LoginOutput execute(LoginInput input) {
        User user = repository.findByEmail(input.email())
                .orElseThrow(() -> new IllegalArgumentException("User not found, you need to register first"));

        if (!hasher.matches(input.password(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

        String token = tokenGenerator.generate(input.email());
        return new LoginOutput(token);
    }
}
