package dev.muliroz.backend.web.dto;

import dev.muliroz.backend.domain.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateTransactionRequest(
        @NotNull(message = "An user is mandatory") UUID userId,
        @NotNull(message = "Transaction category is mandatory") UUID categoryId,
        @Positive(message = "The amount should be greater than zero") BigDecimal amount,
        @NotNull(message = "Transaction type is mandatory") TransactionType type,
        @PastOrPresent(message = "Transaction date shouldn't be in the future") LocalDate date,
        @Size(max = 255, message = "Description lenght cannot exceed 255 characters") String description,
        @NotNull(message = "Idempotency key is missing") UUID idempotencyKey
) {}
