package dev.muliroz.backend.web.dto;

import dev.muliroz.backend.domain.enums.TransactionType;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateTransactionRequest(
        UUID id,
        UUID categoryId,
        @Positive(message = "Transaction amount should be greater than zero") BigDecimal amount,
        TransactionType type,
        @PastOrPresent(message = "Transaction date shouldn't be in the future") LocalDate date,
        @Size(max = 255, message = "Description length cannot exceed 255 characters") String description
) {
}
