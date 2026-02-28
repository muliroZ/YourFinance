package dev.muliroz.backend.infrastructure.resolver;

import dev.muliroz.backend.application.category.list.ListCategoriesInput;
import dev.muliroz.backend.application.category.list.ListCategoriesOutput;
import dev.muliroz.backend.application.category.list.ListCategoriesUseCase;
import dev.muliroz.backend.web.dto.ListCategoriesResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ListCategoriesResolver {

    private final ListCategoriesUseCase useCase;

    public ListCategoriesResolver(ListCategoriesUseCase useCase) {
        this.useCase = useCase;
    }

    public ListCategoriesResponse list(UUID userId) {
        ListCategoriesOutput output = useCase.execute(new ListCategoriesInput(userId));
        return new ListCategoriesResponse(output.categories());
    }
}
