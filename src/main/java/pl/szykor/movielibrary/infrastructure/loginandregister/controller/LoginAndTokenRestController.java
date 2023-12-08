package pl.szykor.movielibrary.infrastructure.loginandregister.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.szykor.movielibrary.domain.loginandregister.LoginAndRegisterFacade;
import pl.szykor.movielibrary.infrastructure.jwt.security.JwtAuthenticatorFacade;
import pl.szykor.movielibrary.infrastructure.loginandregister.controller.dto.LoginResultDto;

@AllArgsConstructor
@RestController
public class LoginAndTokenRestController {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    public LoginResultDto loginAndRetrieveToken(LoginRequestDto loginRequestDto) {
        String token = jwtAuthenticatorFacade.authenticateTheUser(loginRequestDto);
        return new LoginResultDto(loginRequestDto.username(), token);
    }

}
