package dev.muliroz.backend.domain.entity;

import java.util.UUID;

public class Category {

    private final UUID id;
    private final UUID userId;
    private final String name;

    public Category(UUID userId, String name) {
        if (userId == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (name.isBlank() || name.length() > 50) {
            throw new IllegalArgumentException("Category name must be between 1 and 50 characters");
        }

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
    }

    public Category(UUID id, UUID userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
