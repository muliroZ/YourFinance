package dev.muliroz.backend.infrastructure.resolver;

import dev.muliroz.backend.application.transaction.update.UpdateTransactionInput;
import dev.muliroz.backend.application.transaction.update.UpdateTransactionUseCase;
import dev.muliroz.backend.web.dto.UpdateTransactionRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateTransactionResolver {

    private final UpdateTransactionUseCase useCase;

    public UpdateTransactionResolver(UpdateTransactionUseCase useCase) {
        this.useCase = useCase;
    }

    public void update(UUID id, UpdateTransactionRequest request) {
        UpdateTransactionInput input = new UpdateTransactionInput(
                request.id(),
                request.categoryId(),
                request.amount(),
                request.type(),
                request.date(),
                request.description()
        );

        useCase.execute(id, input);
    }
}
