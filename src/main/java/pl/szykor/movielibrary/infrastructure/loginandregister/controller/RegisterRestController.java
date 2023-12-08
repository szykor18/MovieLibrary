package pl.szykor.movielibrary.infrastructure.loginandregister.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public ResponseEntity<RegisterResultDto> registerUser(@RequestBody RegisterRequestDto registerRequest) {
        RegisterResultDto registerResultDto = loginAndRegisterFacade.registerUser(registerRequest);
        return ResponseEntity.ok(registerResultDto);
    }
}
