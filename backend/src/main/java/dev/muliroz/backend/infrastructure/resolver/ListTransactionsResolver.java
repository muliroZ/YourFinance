package dev.muliroz.backend.infrastructure.resolver;

import dev.muliroz.backend.application.transaction.list.ListTransactionsInput;
import dev.muliroz.backend.application.transaction.list.ListTransactionsOutput;
import dev.muliroz.backend.application.transaction.list.ListTransactionsUseCase;
import dev.muliroz.backend.web.dto.ListTransactionsResponse;
import org.springframework.stereotype.Component;

@Component
public class ListTransactionsResolver {

    private final ListTransactionsUseCase useCase;

    public ListTransactionsResolver(ListTransactionsUseCase listTransactionsUseCase) {
        this.useCase = listTransactionsUseCase;
    }

    public ListTransactionsResponse list(ListTransactionsInput input) {
        ListTransactionsOutput output = useCase.execute(input);
        return new ListTransactionsResponse(output.transactions());
    }
}
