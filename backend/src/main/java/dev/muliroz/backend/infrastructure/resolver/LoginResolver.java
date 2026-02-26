package dev.muliroz.backend.infrastructure.resolver;

import dev.muliroz.backend.application.user.login.LoginInput;
import dev.muliroz.backend.application.user.login.LoginOutput;
import dev.muliroz.backend.application.user.login.LoginUseCase;
import dev.muliroz.backend.infrastructure.persistence.model.UserEntity;
import dev.muliroz.backend.web.dto.LoginRequestDTO;
import dev.muliroz.backend.web.dto.LoginResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginResolver {

    private final LoginUseCase loginUseCase;
    private final AuthenticationManager authenticationManager;

    public LoginResolver(LoginUseCase loginUseCase, AuthenticationManager authenticationManager) {
        this.loginUseCase = loginUseCase;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                )
        );

        UserDetails user = (UserDetails) auth.getPrincipal();

        LoginInput input = new LoginInput(user.getUsername(), dto.password());
        LoginOutput output = loginUseCase.execute(input);

        return new LoginResponseDTO(output.token());
    }
}
