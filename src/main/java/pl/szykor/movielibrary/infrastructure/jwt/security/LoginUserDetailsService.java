package pl.szykor.movielibrary.infrastructure.jwt.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.szykor.movielibrary.domain.loginandregister.LoginAndRegisterFacade;
import pl.szykor.movielibrary.domain.loginandregister.dto.UserDto;

import java.util.Collections;

@AllArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
    private final LoginAndRegisterFacade loginAndRegisterFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = loginAndRegisterFacade.findByUsername(username);
        return getUser(userDto);
    }

    private User getUser(UserDto userDto) {
        return new User(userDto.username(), userDto.password(), Collections.emptyList());
    }
}
