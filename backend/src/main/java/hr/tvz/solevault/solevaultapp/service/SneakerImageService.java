package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.SneakerImageCommand;
import hr.tvz.solevault.solevaultapp.model.SneakerImageDTO;

import java.util.List;
import java.util.Optional;

public interface SneakerImageService {
    List<SneakerImageDTO> findAll();

    Optional<SneakerImageDTO> findById(Long id);

    List<SneakerImageDTO> findBySneakerId(Long id);

    SneakerImageDTO add(SneakerImageCommand command);

    Optional<SneakerImageDTO> update(Long id, SneakerImageCommand command);

    void deleteById(Long id);
}
