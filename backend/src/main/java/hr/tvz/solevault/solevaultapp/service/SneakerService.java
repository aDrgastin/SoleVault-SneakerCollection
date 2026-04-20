package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.SneakerCommand;
import hr.tvz.solevault.solevaultapp.model.SneakerDTO;

import java.util.List;
import java.util.Optional;

public interface SneakerService {
    List<SneakerDTO> findAll();

    Optional<SneakerDTO> findById(Long id);

    Optional<SneakerDTO> findByModel(String val);

    List<SneakerDTO> findByColorway(String colorway);

    Optional<SneakerDTO> updateSneaker(Long id, SneakerCommand sneakerCommand);

    Optional<SneakerDTO> addSneaker(SneakerCommand sneakerCommand);

    boolean deleteSneaker(String model);

    boolean deleteSneaker(Long id);
}
