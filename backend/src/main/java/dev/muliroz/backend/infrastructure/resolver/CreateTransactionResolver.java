package dev.muliroz.backend.infrastructure.resolver;

import dev.muliroz.backend.application.transaction.create.CreateTransactionInput;
import dev.muliroz.backend.application.transaction.create.CreateTransactionUseCase;
import dev.muliroz.backend.web.dto.CreateTransactionRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateTransactionResolver {

    private final CreateTransactionUseCase useCase;

    public CreateTransactionResolver(CreateTransactionUseCase createTransactionUseCase) {
        this.useCase = createTransactionUseCase;
    }

    public void create(UUID userId, CreateTransactionRequest request, String idempotencyKey) {
        CreateTransactionInput input = new CreateTransactionInput(
                userId,
                request.categoryId(),
                request.amount(),
                request.type(),
                request.date(),
                request.description(),
                UUID.fromString(idempotencyKey)
        );

        useCase.execute(input);
    }
}
