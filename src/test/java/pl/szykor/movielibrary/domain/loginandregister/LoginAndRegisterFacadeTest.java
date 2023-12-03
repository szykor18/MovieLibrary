package pl.szykor.movielibrary.domain.loginandregister;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import pl.szykor.movielibrary.domain.loginandregister.dto.RegisterRequestDto;
import pl.szykor.movielibrary.domain.loginandregister.dto.RegisterResultDto;
import pl.szykor.movielibrary.domain.loginandregister.dto.UserDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LoginAndRegisterFacadeTest {

    LoginAndRegisterFacade loginAndRegisterFacade = new LoginAndRegisterFacade(new InMemoryUserRepositoryTestImpl());
    @Test
    public void should_successfully_register_user_and_return_success_message() {
        //given
        String username = "Username";
        String password = "Password";
        //when
        RegisterResultDto registerResultDto = loginAndRegisterFacade.registerUser(new RegisterRequestDto(username, password));
        //then
        assertAll(
                () -> assertThat(registerResultDto.id()).isNotNull(),
                () -> assertThat(registerResultDto.username()).isEqualTo("Username"),
                () -> assertThat(registerResultDto.isCreated()).isTrue()
        );
    }

    @Test
    public void should_find_user_by_username_in_database_and_return_correct_user() {
        //given
        String username = "Username";
        String password = "Password";
        //when
        loginAndRegisterFacade.registerUser(new RegisterRequestDto(username, password));
        UserDto user = loginAndRegisterFacade.findByUsername(username);
        //then
        assertAll(
                () -> assertThat(user.id()).isNotNull(),
                () -> assertThat(user.username()).isEqualTo("Username"),
                () -> assertThat(user.password()).isEqualTo("Password")
        );
    }

    @Test
    public void should_throw_username_not_found_exception_when_user_with_given_username_is_not_in_database() {
        //given
        String username = "Username";
        //when
        Throwable throwable = catchThrowable(() -> loginAndRegisterFacade.findByUsername(username));
        //then
        AssertionsForClassTypes.assertThat(throwable)
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User with username Username not found");
    }
}
