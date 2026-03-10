package dev.muliroz.backend.web.controller;

import dev.muliroz.backend.application.transaction.list.ListTransactionsInput;
import dev.muliroz.backend.domain.enums.TransactionType;
import dev.muliroz.backend.infrastructure.persistence.model.UserEntity;
import dev.muliroz.backend.infrastructure.resolver.CreateTransactionResolver;
import dev.muliroz.backend.infrastructure.resolver.ListTransactionsResolver;
import dev.muliroz.backend.infrastructure.resolver.UpdateTransactionResolver;
import dev.muliroz.backend.web.dto.CreateTransactionRequest;
import dev.muliroz.backend.web.dto.ListTransactionsResponse;
import dev.muliroz.backend.web.dto.UpdateTransactionRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final CreateTransactionResolver createResolver;
    private final ListTransactionsResolver listResolver;
    private final UpdateTransactionResolver updateResolver;

    public TransactionController(CreateTransactionResolver createResolver, ListTransactionsResolver listResolver, UpdateTransactionResolver updateResolver) {
        this.createResolver = createResolver;
        this.listResolver = listResolver;
        this.updateResolver = updateResolver;
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody CreateTransactionRequest request,
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @AuthenticationPrincipal UserEntity user
    ) {
        createResolver.create(user.getId(), request, idempotencyKey);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<ListTransactionsResponse> list(
            @AuthenticationPrincipal UserEntity user,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "true") boolean sortByAsc
    ) {
       ListTransactionsResponse response = listResolver.list(new ListTransactionsInput(
               user.getId(),
               categoryId,
               type,
               startDate,
               endDate,
               sortByAsc
       ));

       return ResponseEntity.status(200).body(response);
    }

    @PutMapping
    public ResponseEntity<Void> update(
            @AuthenticationPrincipal UserEntity user,
            @Valid @RequestBody UpdateTransactionRequest request
    ) {
        updateResolver.update(user.getId(), request);
        return ResponseEntity.status(200).build();
    }
}
