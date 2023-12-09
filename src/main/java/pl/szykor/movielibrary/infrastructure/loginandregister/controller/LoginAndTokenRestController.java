package pl.szykor.movielibrary.infrastructure.loginandregister.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.szykor.movielibrary.infrastructure.jwt.security.JwtAuthenticatorFacade;
import pl.szykor.movielibrary.infrastructure.loginandregister.controller.dto.LoginRequestDto;
import pl.szykor.movielibrary.infrastructure.loginandregister.controller.dto.LoginResultDto;

@AllArgsConstructor
@RestController
public class LoginAndTokenRestController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    @PostMapping("/login")
    public ResponseEntity<LoginResultDto> loginAndRetrieveToken(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResultDto loginResultDto = jwtAuthenticatorFacade.authenticateTheUser(loginRequestDto);
        return ResponseEntity.ok(loginResultDto);
    }

}
