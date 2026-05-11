package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandJpaRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String name);
}
