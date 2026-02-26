package dev.muliroz.backend.infrastructure.security;

import dev.muliroz.backend.domain.gateway.PasswordHasher;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityPasswordHasher implements PasswordHasher {

    private final PasswordEncoder encoder;

    public SecurityPasswordHasher(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}
