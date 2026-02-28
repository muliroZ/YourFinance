package dev.muliroz.backend.domain.gateway;

import dev.muliroz.backend.domain.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository {
    void save(Category category);
    boolean existsByUserIdAndName(UUID userId, String name);
    List<Category> list(UUID userId);
}
