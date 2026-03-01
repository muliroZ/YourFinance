package dev.muliroz.backend.web.controller;

import dev.muliroz.backend.application.transaction.list.ListTransactionsInput;
import dev.muliroz.backend.domain.enums.TransactionType;
import dev.muliroz.backend.infrastructure.persistence.model.UserEntity;
import dev.muliroz.backend.infrastructure.resolver.CreateTransactionResolver;
import dev.muliroz.backend.infrastructure.resolver.ListTransactionsResolver;
import dev.muliroz.backend.web.dto.CreateTransactionRequest;
import dev.muliroz.backend.web.dto.ListTransactionsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final CreateTransactionResolver createResolver;
    private final ListTransactionsResolver listResolver;

    public TransactionController(CreateTransactionResolver createResolver, ListTransactionsResolver listResolver) {
        this.createResolver = createResolver;
        this.listResolver = listResolver;
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
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "true") boolean sortByAsc
    ) {
       ListTransactionsResponse response = listResolver.list(new ListTransactionsInput(
               user.getId(),
               type,
               startDate,
               endDate,
               sortByAsc
       ));

       return ResponseEntity.status(200).body(response);
    }
}
