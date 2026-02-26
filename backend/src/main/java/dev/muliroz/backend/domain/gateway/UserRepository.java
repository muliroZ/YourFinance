package dev.muliroz.backend.domain.gateway;

import dev.muliroz.backend.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
