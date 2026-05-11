package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceHistoryJpaRepository extends JpaRepository<PriceHistory, Long> {
    List<PriceHistory> findBySneakerId(Long id);
}
