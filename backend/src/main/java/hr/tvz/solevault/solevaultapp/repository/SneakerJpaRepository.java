package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SneakerJpaRepository extends JpaRepository<Sneaker, Long> {
    Optional<Sneaker> findByModel(String model);

    Optional<Sneaker> findByColorway(String colorway);

    void deleteByModel(String model);
}
