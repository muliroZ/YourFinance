package dev.muliroz.backend.application.category.create;

import java.util.UUID;

public record CreateCategoryInput(
        UUID userId,
        String name
) {}
