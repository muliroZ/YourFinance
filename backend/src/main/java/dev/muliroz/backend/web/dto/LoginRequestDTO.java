package dev.muliroz.backend.web.dto;

public record LoginRequestDTO (
        String email,
        String password
) {}
