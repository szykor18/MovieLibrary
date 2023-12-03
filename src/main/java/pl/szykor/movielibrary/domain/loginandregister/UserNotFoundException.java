package pl.szykor.movielibrary.domain.loginandregister;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super("User with username " + username + " not found");
    }
}
