package dev.muliroz.backend.infrastructure.persistence.repository;

import dev.muliroz.backend.domain.entity.User;
import dev.muliroz.backend.domain.gateway.UserRepository;
import dev.muliroz.backend.infrastructure.persistence.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository repository;

    public JpaUserRepository(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );

        repository.save(userEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserEntity userEntity = repository.findByEmail(email);
        User user = new User(
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );

        return Optional.of(user);
    }
}
