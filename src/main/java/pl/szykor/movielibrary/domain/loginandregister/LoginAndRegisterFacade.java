package pl.szykor.movielibrary.domain.loginandregister;

import lombok.AllArgsConstructor;
import pl.szykor.movielibrary.domain.loginandregister.dto.RegisterRequestDto;
import pl.szykor.movielibrary.domain.loginandregister.dto.RegisterResultDto;
import pl.szykor.movielibrary.domain.loginandregister.dto.UserDto;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    private final UserRepository userRepository;

    public RegisterResultDto registerUser(RegisterRequestDto registerRequest) {
        User user = UserMapper.mapFromRegisterToUser(registerRequest);
        User savedUser = userRepository.save(user);
        return RegisterResultDto.builder()
                .id(savedUser.id())
                .username(savedUser.username())
                .isCreated(true)
                .build();
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::mapFromUserToDto)
                .orElseThrow(() -> new UserNotFoundException(username));
    }
}
