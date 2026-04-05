package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.Sneaker;

import java.util.List;
import java.util.Optional;

public interface SneakerRepository {
    List<Sneaker> findAll();

    Optional<Sneaker> findById(Long id);

    Optional<Sneaker> findByModel(String val);

    List<Sneaker> findByColorway(String colorway);

    Optional<Sneaker> addSneaker(Sneaker sneaker);

    boolean deleteSneaker(String model);

    boolean deleteSneaker(Long id);
}
