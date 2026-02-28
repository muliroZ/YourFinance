package dev.muliroz.backend.web.dto;

import dev.muliroz.backend.domain.entity.Category;

import java.util.List;

public record ListCategoriesResponse(
        List<Category> categories
) {}