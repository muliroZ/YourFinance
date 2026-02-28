package dev.muliroz.backend.application.transaction.create;

import dev.muliroz.backend.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateTransactionInput(
        UUID userId,
        UUID categoryId,
        BigDecimal amount,
        TransactionType type,
        LocalDate date,
        String description,
        UUID idempotencyKey
) {}
