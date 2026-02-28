package dev.muliroz.backend.web.dto;

import dev.muliroz.backend.domain.entity.Transaction;

import java.util.List;

public record ListTransactionsResponse(
        List<Transaction> transactions
) {}
