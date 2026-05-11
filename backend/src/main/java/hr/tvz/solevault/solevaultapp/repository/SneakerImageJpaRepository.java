package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.SneakerImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SneakerImageJpaRepository extends JpaRepository<SneakerImage, Long> {
    List<SneakerImage> findBySneakerId(Long sneakerId);
}
