package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.Sneaker;

import java.util.List;

public interface SneakerRepository {
    List<Sneaker> findAll();

    List<Sneaker> findById(Long id);

    List<Sneaker> findByModel(String val);
}
