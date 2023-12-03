package pl.szykor.movielibrary.domain.loginandregister;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);
}
