package dev.muliroz.backend.infrastructure.persistence.repository;

import dev.muliroz.backend.domain.entity.Category;
import dev.muliroz.backend.domain.gateway.CategoryRepository;
import dev.muliroz.backend.infrastructure.persistence.model.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final SpringDataCategoryRepository repository;

    public JpaCategoryRepository(SpringDataCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Category category) {
        CategoryEntity entity = new CategoryEntity(
                category.getId(),
                category.getUserId(),
                category.getName()
        );

        repository.save(entity);
    }

    @Override
    public boolean existsByUserIdAndName(UUID userId, String name) {
        return repository.existsByUserIdAndName(userId, name);
    }

    @Override
    public List<Category> list(UUID userId) {
        List<CategoryEntity> entityList = repository.findAllByUserId(userId);

        return entityList.stream()
                .map(CategoryEntity::toDomain)
                .toList();
    }
}
