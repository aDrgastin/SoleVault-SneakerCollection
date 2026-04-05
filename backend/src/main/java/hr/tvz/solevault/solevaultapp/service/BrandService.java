package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.BrandCommand;
import hr.tvz.solevault.solevaultapp.model.BrandDTO;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandDTO> findAll();

    Optional<BrandDTO> findById(Long id);

    Optional<BrandDTO> add(BrandCommand brandCommand);
}
