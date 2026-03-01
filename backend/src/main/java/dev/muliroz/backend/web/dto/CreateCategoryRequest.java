package dev.muliroz.backend.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateCategoryRequest(
        @NotBlank(message = "Name is mandatory")
        @Size(max = 50, message = "Name lenght cannot exceed 50 characters")
        String name
) {}
