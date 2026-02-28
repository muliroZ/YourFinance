package dev.muliroz.backend.application.category.list;

import dev.muliroz.backend.domain.entity.Category;
import dev.muliroz.backend.domain.gateway.CategoryRepository;

import java.util.List;

public class ListCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public ListCategoriesUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ListCategoriesOutput execute(ListCategoriesInput input) {
        if (input.userId() == null) {
            throw new IllegalArgumentException("User not found");
        }

        List<Category> categories = categoryRepository.list(input.userId());
        return new ListCategoriesOutput(categories);
    }
}
