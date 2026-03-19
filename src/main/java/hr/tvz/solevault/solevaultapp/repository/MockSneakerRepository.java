package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.Sneaker;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MockSneakerRepository implements SneakerRepository {
    Sneaker sneaker1 = new Sneaker(1l, "Air Force", 1l, 44, "white/green", BigDecimal.valueOf(77), BigDecimal.valueOf(66), "used", LocalDate.now());
    Sneaker sneaker2 = new Sneaker(2l, "Yeezy", 2l, 44, "black/white", BigDecimal.valueOf(120), BigDecimal.valueOf(105), "used", LocalDate.now());

    @Override
    public List<Sneaker> findAll() {
        return List.of(sneaker1, sneaker2);
    }

    @Override
    public List<Sneaker> findById(Long id) {
        return List.of(sneaker1, sneaker2).stream().filter(sneaker -> sneaker.getId().equals(id)).toList();
    }

    @Override
    public List<Sneaker> findByModel(String val) {
        return List.of(sneaker1, sneaker2).stream().filter(sneaker -> sneaker.getModel().equals(val)).toList();
    }
}
