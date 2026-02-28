package dev.muliroz.backend.application.category.list;

import dev.muliroz.backend.domain.entity.Category;

import java.util.List;

public record ListCategoriesOutput(
        List<Category> categories
) {}
