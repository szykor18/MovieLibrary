package pl.szykor.movielibrary.infrastructure.jwt.security;

import org.springframework.stereotype.Component;
import pl.szykor.movielibrary.infrastructure.loginandregister.controller.LoginRequestDto;

@Component
public class JwtAuthenticatorFacade {


    public String authenticateTheUser(LoginRequestDto loginRequestDto) {
        return null;
    }
}
