package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.*;
import hr.tvz.solevault.solevaultapp.repository.SneakerImageJpaRepository;
import hr.tvz.solevault.solevaultapp.repository.SneakerJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class SneakerImageServiceImpl implements SneakerImageService {
    private SneakerImageJpaRepository repository;
    private SneakerJpaRepository sneakerJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SneakerImageDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SneakerImageDTO> findById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SneakerImageDTO> findBySneakerId(Long id) {
        return repository.findBySneakerId(id).stream().map(this::toDTO).toList();
    }

    @Override
    public SneakerImageDTO add(SneakerImageCommand command) {
        Sneaker sneaker = sneakerJpaRepository.findById(command.getSneakerId()).orElseThrow(() -> new RuntimeException("Sneaker not found with id: " + command.getSneakerId()));
        return toDTO(repository.save(new SneakerImage(null, command.getUrl(), command.getTag(), command.getDescription(), sneaker)));
    }

    @Override
    public Optional<SneakerImageDTO> update(Long id, SneakerImageCommand command) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }
        Sneaker sneaker = sneakerJpaRepository.findById(command.getSneakerId()).orElseThrow(() -> new RuntimeException("Sneaker not found with id: " + command.getSneakerId()));
        return Optional.of(
                toDTO(repository.save(new SneakerImage(id, command.getUrl(), command.getTag(), command.getDescription(), sneaker)))
        );
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private SneakerImageDTO toDTO(SneakerImage sneakerImage) {
        Sneaker s = sneakerImage.getSneaker();
        Brand b = s.getBrand();
        BrandDTO brandDTO = new BrandDTO(b.getId(), b.getName(), b.getCountry(), b.getFounded(), b.getLogoUrl());
        SneakerDTO sneakerDTO = new SneakerDTO(s.getId(), s.getModel(), brandDTO, s.getSize(), s.getColorway(), s.getBuyPrice(), s.getCurrentValue(), s.getCurrentValue().subtract(s.getBuyPrice()), s.getCondition());
        return new SneakerImageDTO(sneakerImage.getId(), sneakerImage.getUrl(), sneakerImage.getTag(), sneakerImage.getDescription(), sneakerDTO);
    }
}
