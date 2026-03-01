package dev.muliroz.backend.infrastructure.security;

import dev.muliroz.backend.domain.entity.User;
import dev.muliroz.backend.infrastructure.persistence.repository.JpaUserRepository;
import dev.muliroz.backend.infrastructure.persistence.repository.SpringDataUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SpringDataUserRepository repository;

    public CustomUserDetailsService(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
