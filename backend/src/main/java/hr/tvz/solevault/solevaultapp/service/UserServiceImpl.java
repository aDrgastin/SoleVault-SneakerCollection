package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.User;
import hr.tvz.solevault.solevaultapp.model.UserCommand;
import hr.tvz.solevault.solevaultapp.model.UserDTO;
import hr.tvz.solevault.solevaultapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(this::toDto);
    }

    @Override
    public Optional<UserDTO> add(UserCommand userCommand) {
        User newUser = new User(userRepository.findAll().size() + 1L, userCommand.getUsername(), userCommand.getPassword(), userCommand.getEmail(), "USER");
        return userRepository.addUser(newUser).map(this::toDto);
    }

    private UserDTO toDto(User user) {
        return new UserDTO(user.getUsername(), user.getEmail(), user.getRole());
    }
}
