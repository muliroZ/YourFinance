package dev.muliroz.backend.web.controller;

import dev.muliroz.backend.infrastructure.persistence.model.UserEntity;
import dev.muliroz.backend.infrastructure.resolver.CreateCategoryResolver;
import dev.muliroz.backend.infrastructure.resolver.ListCategoriesResolver;
import dev.muliroz.backend.web.dto.CreateCategoryRequest;
import dev.muliroz.backend.web.dto.ListCategoriesResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CreateCategoryResolver createCategoryResolver;
    private final ListCategoriesResolver listCategoriesResolver;

    public CategoryController(CreateCategoryResolver createCategoryResolver, ListCategoriesResolver listCategoriesResolver) {
        this.createCategoryResolver = createCategoryResolver;
        this.listCategoriesResolver = listCategoriesResolver;
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody CreateCategoryRequest request,
            @AuthenticationPrincipal UserEntity user
    ) {
        createCategoryResolver.create(user.getId(), request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<ListCategoriesResponse> list(@AuthenticationPrincipal UserEntity user) {
        ListCategoriesResponse response = listCategoriesResolver.list(user.getId());
        return ResponseEntity.ok(response);
    }
}
