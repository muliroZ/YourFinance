package dev.muliroz.backend.infrastructure.config;

import dev.muliroz.backend.domain.gateway.PasswordHasher;
import dev.muliroz.backend.domain.gateway.TokenGenerator;
import dev.muliroz.backend.domain.gateway.UserRepository;
import dev.muliroz.backend.infrastructure.persistence.repository.JpaUserRepository;
import dev.muliroz.backend.infrastructure.persistence.repository.SpringDataUserRepository;
import dev.muliroz.backend.infrastructure.security.JwtGenerator;
import dev.muliroz.backend.infrastructure.security.SecurityPasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InfrastructureConfig {

    @Bean
    public UserRepository userRepository(SpringDataUserRepository springDataUserRepository) {
        return new JpaUserRepository(springDataUserRepository);
    }

    @Bean
    public TokenGenerator tokenGenerator() {
        return new JwtGenerator();
    }

    @Bean
    public PasswordHasher passwordHasher(PasswordEncoder encoder) {
        return new SecurityPasswordHasher(encoder);
    }
}
