package dev.muliroz.backend.web.controller;

import dev.muliroz.backend.infrastructure.resolver.LoginResolver;
import dev.muliroz.backend.infrastructure.resolver.RegisterResolver;
import dev.muliroz.backend.web.dto.LoginRequest;
import dev.muliroz.backend.web.dto.LoginResponse;
import dev.muliroz.backend.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterResolver registerResolver;
    private final LoginResolver loginResolver;

    public AuthController(RegisterResolver registerResolver, LoginResolver loginResolver) {
        this.registerResolver = registerResolver;
        this.loginResolver = loginResolver;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        registerResolver.register(request);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = loginResolver.login(request);
        return ResponseEntity.status(200).body(response);
    }
}
