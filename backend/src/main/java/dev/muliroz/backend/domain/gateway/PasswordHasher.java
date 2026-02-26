package dev.muliroz.backend.domain.gateway;

public interface PasswordHasher {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
