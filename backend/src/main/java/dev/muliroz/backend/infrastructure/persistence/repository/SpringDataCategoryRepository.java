package dev.muliroz.backend.infrastructure.persistence.repository;

import dev.muliroz.backend.infrastructure.persistence.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    boolean existsByUserIdAndName(UUID userId, String name);

    List<CategoryEntity> findAllByUserId(UUID userId);
}
