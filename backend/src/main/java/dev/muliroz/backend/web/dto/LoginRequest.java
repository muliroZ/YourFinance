package dev.muliroz.backend.web.dto;

public record LoginRequest(
        String email,
        String password
) {}
