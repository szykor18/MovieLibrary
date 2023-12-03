package pl.szykor.movielibrary.domain.loginandregister;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepositoryTestImpl implements UserRepository {

    private Map<String, User> users = new ConcurrentHashMap<>();
    @Override
    public User save(User entity) {
        String id = UUID.randomUUID().toString();
        User user = User.builder()
                .id(id)
                .username(entity.username())
                .password(entity.password())
                .build();
        users.put(user.username(), user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
