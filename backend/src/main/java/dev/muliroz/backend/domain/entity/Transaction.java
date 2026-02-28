package dev.muliroz.backend.domain.entity;

import dev.muliroz.backend.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private final UUID id;
    private final UUID userId;
    private final UUID categoryId;
    private final BigDecimal amount;
    private final TransactionType type;
    private final LocalDate date;
    private final String description;
    private final UUID idempotencyKey;
    private final LocalDateTime createdAt;

    public Transaction(UUID userId, UUID categoryId, BigDecimal amount, TransactionType type, LocalDate date, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount should be positive");
        }

        if (type == null) {
            throw new IllegalArgumentException("Transaction type cannot be null");
        }

        if (categoryId == null) {
            throw new IllegalArgumentException("Transaction category cannot be null or empty");
        }

        if (date == null || date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Transaction date cannot be null or in the future");
        }

        if (description != null && description.length() > 255) {
            throw new IllegalArgumentException("Description cannot exceed 255 characters");
        }

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.date = date;
        this.description = description;
        this.idempotencyKey = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public Transaction(UUID id, UUID userId, UUID categoryId, BigDecimal amount, TransactionType type, LocalDate date, String description, UUID idempotencyKey, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.date = date;
        this.description = description;
        this.idempotencyKey = idempotencyKey;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public TransactionType getType() {
        return type;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
