package dev.muliroz.backend.application.user.register;

import dev.muliroz.backend.domain.entity.User;
import dev.muliroz.backend.domain.gateway.PasswordHasher;
import dev.muliroz.backend.domain.gateway.UserRepository;

public class RegisterUserUseCase {

    private final UserRepository repository;
    private final PasswordHasher hasher;

    public RegisterUserUseCase(UserRepository repository, PasswordHasher hasher) {
        this.repository = repository;
        this.hasher = hasher;
    }

    public void execute(RegisterUserInput input) {
        if (repository.existsByEmail(input.email())) {
            throw new IllegalArgumentException("User already registered");
        }

        User user = new User(
                input.name(),
                input.email(),
                hasher.encode(input.password())
        );

        repository.save(user);
    }
}
