package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class MockUserRepository implements UserRepository {
    User user1 = new User(1L, "pero", "peropass", "pperic@mail.com", "ADMIN");
    User user2 = new User(2L, "ivo", "ivopass", "iivic@mail.com", "MOD");
    User user3 = new User(3L, "djuro", "djuropass", "djuric@mail.com", "USER");
    List<User> users = List.of(user1, user2, user3);

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<User> addUser(User user) {
        if (users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
            return Optional.empty();
        }
        User newUser = new User(users.size() + 1L, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
        users = Stream.concat(users.stream(), Stream.of(newUser)).toList();
        return Optional.of(newUser);
    }
}
