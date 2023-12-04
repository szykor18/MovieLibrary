package pl.szykor.movielibrary.domain.loginandregister;

import pl.szykor.movielibrary.domain.loginandregister.dto.RegisterRequestDto;
import pl.szykor.movielibrary.domain.loginandregister.dto.UserDto;

class UserMapper {

    static User mapFromRegisterToUser(RegisterRequestDto registerRequestDto) {
        return User.builder()
                .username(registerRequestDto.username())
                .password(registerRequestDto.password())
                .build();
    }

    static UserDto mapFromUserToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
