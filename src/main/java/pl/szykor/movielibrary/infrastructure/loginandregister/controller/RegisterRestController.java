package pl.szykor.movielibrary.infrastructure.loginandregister.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.szykor.movielibrary.domain.loginandregister.LoginAndRegisterFacade;
import pl.szykor.movielibrary.domain.loginandregister.dto.RegisterRequestDto;
import pl.szykor.movielibrary.domain.loginandregister.dto.RegisterResultDto;

@RestController
@AllArgsConstructor
public class RegisterRestController {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<RegisterResultDto> registerUser(@RequestBody RegisterRequestDto registerRequest) {
        String username = registerRequest.username();
        String password = bCryptPasswordEncoder.encode(registerRequest.password());
        RegisterResultDto registerResultDto = loginAndRegisterFacade.registerUser(
                new RegisterRequestDto(username, password));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResultDto);
    }
}
