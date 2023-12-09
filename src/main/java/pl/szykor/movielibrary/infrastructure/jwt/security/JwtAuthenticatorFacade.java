package pl.szykor.movielibrary.infrastructure.jwt.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import pl.szykor.movielibrary.infrastructure.loginandregister.controller.dto.LoginRequestDto;
import pl.szykor.movielibrary.infrastructure.loginandregister.controller.dto.LoginResultDto;

import java.time.*;

@Component
@AllArgsConstructor
public class JwtAuthenticatorFacade {

    private final AuthenticationManager authenticationManager;
    private final Clock clock;

    public LoginResultDto authenticateTheUser(LoginRequestDto loginRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.username(),
                loginRequestDto.password()));
        User user = (User) authenticate.getPrincipal();
        String username = user.getUsername();
        String token = createToken(user);
        return new LoginResultDto(username, token);
    }

    private String createToken(User user) {
        String secretKey = "secretKey";
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expiresAt = now.plus(Duration.ofDays(30));
        String issuer = "movie-library-backend-service";
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
