package pl.szykor.movielibrary.infrastructure.jwt.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import pl.szykor.movielibrary.infrastructure.loginandregister.controller.dto.LoginRequestDto;
import pl.szykor.movielibrary.infrastructure.loginandregister.controller.dto.LoginResultDto;

@Component
@AllArgsConstructor
public class JwtAuthenticatorFacade {

    private final AuthenticationManager authenticationManager;

    public LoginResultDto authenticateTheUser(LoginRequestDto loginRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.username(),
                loginRequestDto.password()));
        User user = (User) authenticate.getPrincipal();
        String username = user.getUsername();
        String token = createToken(user);
        return new LoginResultDto(username, token);
    }

    private String createToken(User user) {
        return null;
    }
}
