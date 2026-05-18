package hr.tvz.solevault.solevaultapp.security;

import hr.tvz.solevault.solevaultapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
