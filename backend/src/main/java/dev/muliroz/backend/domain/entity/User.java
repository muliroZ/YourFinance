package dev.muliroz.backend.domain.entity;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String name;
    private final String email;
    private final String password;

    public User(String name, String email, String password) {
        if (name.length() > 50 || name.isBlank()) {
            throw new IllegalArgumentException("Invalid name");
        }

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
                || email.length() > 100) {
            throw new IllegalArgumentException("Invalid email");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
