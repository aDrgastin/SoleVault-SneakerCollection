package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.Sneaker;
import hr.tvz.solevault.solevaultapp.model.SneakerDTO;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class MockSneakerRepository implements SneakerRepository {
    private Sneaker sneaker1 = new Sneaker(1l, "Air Force", 1l, BigDecimal.valueOf(44), "white/green", BigDecimal.valueOf(77), BigDecimal.valueOf(66), "USED", LocalDate.now());
    private Sneaker sneaker2 = new Sneaker(2l, "Yeezy", 2l, BigDecimal.valueOf(41), "black/white", BigDecimal.valueOf(120), BigDecimal.valueOf(105), "USED", LocalDate.now());
    private Sneaker sneaker3 = new Sneaker(3l, "Air Max", 1l, BigDecimal.valueOf(37), "black/white", BigDecimal.valueOf(134), BigDecimal.valueOf(134), "NEW", LocalDate.now());
    List<Sneaker> sneakers = new ArrayList<>(List.of(sneaker1, sneaker2, sneaker3));

    @Override
    public List<Sneaker> findAll() {
        return sneakers;
    }

    @Override
    public Optional<Sneaker> findById(Long id) {
        return sneakers.stream().filter(sneaker -> sneaker.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Sneaker> findByModel(String val) {
        return sneakers.stream().filter(sneaker -> sneaker.getModel().equals(val)).findFirst();
    }

    @Override
    public List<Sneaker> findByColorway(String colorway) {
        return sneakers.stream().filter(sneaker -> sneaker.getColorway().equals(colorway)).toList();
    }

    @Override
    public Optional<Sneaker> addSneaker(Sneaker sneaker) {
        if (sneakers.stream().anyMatch(s -> s.getModel().equals(sneaker.getModel()))) {
            return Optional.empty();
        }
        sneakers.add(sneaker);
        return Optional.of(sneaker);
    }

    @Override
    public Optional<Sneaker> updateSneaker(Sneaker sneaker) {
        return sneakers.stream()
                .filter(s -> s.getId().equals(sneaker.getId()))
                .findFirst()
                .map(old -> {
                    sneakers.set(sneakers.indexOf(old), sneaker);
                    return sneaker;
                });
    }

    @Override
    public boolean deleteSneaker(String model) {
        if (!sneakers.stream().filter(sneaker -> sneaker.getModel().equals(model)).findFirst().isPresent()) {
            return false;
        }
        sneakers.removeIf(s -> s.getModel().equals(model));
        return true;
    }

    @Override
    public boolean deleteSneaker(Long id) {
        if (!sneakers.stream().filter(sneaker -> sneaker.getId().equals(id)).findFirst().isPresent()) {
            return false;
        }
        sneakers.removeIf(s -> s.getId().equals(id));
        return true;
    }
}
