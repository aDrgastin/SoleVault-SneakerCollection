package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.Sneaker;
import hr.tvz.solevault.solevaultapp.model.SneakerDTO;

import java.util.List;
import java.util.Optional;

public interface SneakerService {
    List<SneakerDTO> findAll();

    Optional<SneakerDTO> findById(Long id);

    Optional<SneakerDTO> findByModel(String val);

    SneakerDTO addSneaker(SneakerDTO sneaker);

    void deleteSneaker(Long id);
}
