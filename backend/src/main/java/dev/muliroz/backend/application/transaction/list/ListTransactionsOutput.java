package dev.muliroz.backend.application.transaction.list;

import dev.muliroz.backend.domain.entity.Transaction;

import java.util.List;

public record ListTransactionsOutput(
        List<Transaction> transactions
) {}
