package dev.muliroz.backend.application.user.register;

public record RegisterUserInput(
        String name,
        String email,
        String password
) {}
