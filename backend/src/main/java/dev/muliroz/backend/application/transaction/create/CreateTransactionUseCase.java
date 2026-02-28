package dev.muliroz.backend.application.transaction.create;

import dev.muliroz.backend.domain.entity.Transaction;
import dev.muliroz.backend.domain.gateway.TransactionRepository;
import dev.muliroz.backend.domain.gateway.UserRepository;

public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public CreateTransactionUseCase(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public void execute(CreateTransactionInput input) {
        if (!userRepository.existsById(input.userId())) {
            throw new IllegalArgumentException("User not found");
        }

        if (transactionRepository.existsByIdempotencyKey(input.idempotencyKey())) {
            throw new IllegalArgumentException("Transaction already exists");
        }

        Transaction transaction = new Transaction(
                input.userId(),
                input.categoryId(),
                input.amount(),
                input.type(),
                input.date(),
                input.description()
        );

        transactionRepository.save(transaction);
    }
}
