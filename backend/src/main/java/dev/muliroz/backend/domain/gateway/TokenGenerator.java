package dev.muliroz.backend.domain.gateway;

public interface TokenGenerator {
    String generate(String email);
}
