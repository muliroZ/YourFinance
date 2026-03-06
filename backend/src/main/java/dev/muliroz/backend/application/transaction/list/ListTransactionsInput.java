package dev.muliroz.backend.application.transaction.list;

import dev.muliroz.backend.domain.enums.TransactionType;

import java.time.LocalDate;
import java.util.UUID;

public record ListTransactionsInput(
        UUID userId,
        UUID categoryId,
        TransactionType type,
        LocalDate startDate,
        LocalDate endDate,
        boolean sortByAsc
) {}
