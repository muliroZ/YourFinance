package dev.muliroz.backend.infrastructure.config;

import dev.muliroz.backend.application.transaction.create.CreateTransactionUseCase;
import dev.muliroz.backend.application.transaction.list.ListTransactionsUseCase;
import dev.muliroz.backend.application.transaction.update.UpdateTransactionUseCase;
import dev.muliroz.backend.domain.gateway.TransactionRepository;
import dev.muliroz.backend.domain.gateway.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionUseCaseConfig {

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(
            TransactionRepository transactionRepository,
            UserRepository userRepository
    ) {
        return new CreateTransactionUseCase(
                transactionRepository,
                userRepository
        );
    }

    @Bean
    public ListTransactionsUseCase listTransactionsUseCase(
            TransactionRepository transactionRepository,
            UserRepository userRepository
    ) {
        return new ListTransactionsUseCase(
                transactionRepository,
                userRepository
        );
    }

    @Bean
    public UpdateTransactionUseCase updateTransactionUseCase(TransactionRepository transactionRepository) {
        return new UpdateTransactionUseCase(transactionRepository);
    }
}
