package dev.muliroz.backend.infrastructure.resolver;

import dev.muliroz.backend.application.user.login.LoginInput;
import dev.muliroz.backend.application.user.login.LoginOutput;
import dev.muliroz.backend.application.user.login.LoginUseCase;
import dev.muliroz.backend.web.dto.LoginRequest;
import dev.muliroz.backend.web.dto.LoginResponse;
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

    public LoginResponse login(LoginRequest dto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                )
        );

        UserDetails user = (UserDetails) auth.getPrincipal();

        LoginInput input = new LoginInput(user.getUsername(), dto.password());
        LoginOutput output = loginUseCase.execute(input);

        return new LoginResponse(output.token());
    }
}
