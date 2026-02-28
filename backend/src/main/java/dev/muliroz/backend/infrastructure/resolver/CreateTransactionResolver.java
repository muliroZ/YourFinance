package dev.muliroz.backend.infrastructure.resolver;

import dev.muliroz.backend.application.transaction.create.CreateTransactionInput;
import dev.muliroz.backend.application.transaction.create.CreateTransactionUseCase;
import dev.muliroz.backend.web.dto.CreateTransactionRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateTransactionResolver {

    private final CreateTransactionUseCase useCase;

    public CreateTransactionResolver(CreateTransactionUseCase createTransactionUseCase) {
        this.useCase = createTransactionUseCase;
    }

    public void create(CreateTransactionRequest request) {
        CreateTransactionInput input = new CreateTransactionInput(
                request.userId(),
                request.categoryId(),
                request.amount(),
                request.type(),
                request.date(),
                request.description(),
                request.idempotencyKey()
        );

        useCase.execute(input);
    }
}
