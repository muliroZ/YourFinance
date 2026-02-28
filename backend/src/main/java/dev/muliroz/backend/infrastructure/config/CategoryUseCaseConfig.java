package dev.muliroz.backend.infrastructure.config;

import dev.muliroz.backend.application.category.create.CreateCategoryUseCase;
import dev.muliroz.backend.application.category.list.ListCategoriesUseCase;
import dev.muliroz.backend.domain.gateway.CategoryRepository;
import dev.muliroz.backend.domain.gateway.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(
            CategoryRepository categoryRepository,
            UserRepository userRepository
    ) {
        return new CreateCategoryUseCase(
                categoryRepository,
                userRepository
        );
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase(CategoryRepository categoryRepository) {
        return new ListCategoriesUseCase(categoryRepository);
    }
}
