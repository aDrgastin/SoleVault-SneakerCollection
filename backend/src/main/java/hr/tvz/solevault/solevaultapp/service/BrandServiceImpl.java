package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.Brand;
import hr.tvz.solevault.solevaultapp.model.BrandCommand;
import hr.tvz.solevault.solevaultapp.model.BrandDTO;
import hr.tvz.solevault.solevaultapp.repository.BrandJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandJpaRepository brandRepository;

    @Override
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public Optional<BrandDTO> findById(Long id) {
        return brandRepository.findById(id).map(this::toDto);
    }

    @Override
    public Optional<BrandDTO> addBrand(BrandCommand brandCommand) {
        return Optional.of(
                toDto((brandRepository.save(new Brand(null, brandCommand.getName(), brandCommand.getCountry(), brandCommand.getFounded(), brandCommand.getLogoUrl()))))
        );
    }

    @Override
    public Optional<BrandDTO> updateBrand(Long id, BrandCommand brandCommand) {
        if (brandRepository.existsById(id)) return Optional.empty();
        return Optional.of(
                toDto(brandRepository.save(new Brand(id, brandCommand.getName(), brandCommand.getCountry(), brandCommand.getFounded(), brandCommand.getLogoUrl())))
        );
    }

    @Override
    public boolean deleteBrand(Long id) {
        try {
            brandRepository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private BrandDTO toDto(Brand brand) {
        return new BrandDTO(brand.getId(), brand.getName(), brand.getCountry(), brand.getFounded(), brand.getLogoUrl());
    }
}
