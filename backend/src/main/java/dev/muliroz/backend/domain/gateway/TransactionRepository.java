package dev.muliroz.backend.domain.gateway;

import dev.muliroz.backend.domain.entity.Transaction;
import dev.muliroz.backend.domain.enums.TransactionType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository {
    void save(Transaction transaction);
    boolean existsByIdempotencyKey(UUID idempotencyKey);
    List<Transaction> search(UUID userId, TransactionType type, LocalDate startDate, LocalDate endDate, Boolean sortByAsc);
}
