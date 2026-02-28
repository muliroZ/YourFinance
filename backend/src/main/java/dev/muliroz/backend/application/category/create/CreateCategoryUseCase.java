package dev.muliroz.backend.application.category.create;

import dev.muliroz.backend.domain.entity.Category;
import dev.muliroz.backend.domain.gateway.CategoryRepository;
import dev.muliroz.backend.domain.gateway.UserRepository;

public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public void execute(CreateCategoryInput input) {
        if (!userRepository.existsById(input.userId())) {
            throw new IllegalArgumentException("User not found");
        }

        if (categoryRepository.existsByUserIdAndName(input.userId(), input.name())) {
            throw new IllegalArgumentException("Category name already exists for this user");
        }

        Category category = new Category(input.userId(), input.name());
        categoryRepository.save(category);
    }
}
