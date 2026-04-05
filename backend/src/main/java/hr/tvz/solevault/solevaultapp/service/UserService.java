package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.UserCommand;
import hr.tvz.solevault.solevaultapp.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();

    Optional<UserDTO> findById(Long id);

    Optional<UserDTO> add(UserCommand userCommand);
}
