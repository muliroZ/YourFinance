package dev.muliroz.backend.application.transaction.update;

import dev.muliroz.backend.domain.entity.Transaction;
import dev.muliroz.backend.domain.gateway.TransactionRepository;

import java.util.Optional;
import java.util.UUID;

public class UpdateTransactionUseCase {

    private final TransactionRepository repository;

    public UpdateTransactionUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, UpdateTransactionInput input) {
        Transaction oldTransaction = repository.findById(input.id())
                .orElseThrow(RuntimeException::new);

        if (!oldTransaction.getUserId().equals(id)) throw new IllegalArgumentException();

        Transaction newTransaction = new Transaction(
                oldTransaction.getId(),
                oldTransaction.getUserId(),
                input.categoryId(),
                input.amount(),
                input.type(),
                input.date(),
                input.description(),
                oldTransaction.getIdempotencyKey(),
                oldTransaction.getCreatedAt()
        );

        repository.save(newTransaction);
    }
}
