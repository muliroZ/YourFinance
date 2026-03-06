package dev.muliroz.backend.infrastructure.resolver;

import dev.muliroz.backend.application.category.create.CreateCategoryInput;
import dev.muliroz.backend.application.category.create.CreateCategoryUseCase;
import dev.muliroz.backend.web.dto.CreateCategoryRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCategoryResolver {

    private final CreateCategoryUseCase useCase;

    public CreateCategoryResolver(CreateCategoryUseCase useCase) {
        this.useCase = useCase;
    }

    public void create(UUID userId, CreateCategoryRequest request) {
        CreateCategoryInput input = new CreateCategoryInput(userId, request.name());
        useCase.execute(input);
    }
}
