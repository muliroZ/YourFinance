package dev.muliroz.backend.application.transaction.update;

import dev.muliroz.backend.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateTransactionInput(
        UUID id,
        UUID categoryId,
        BigDecimal amount,
        TransactionType type,
        LocalDate date,
        String description
) {}
