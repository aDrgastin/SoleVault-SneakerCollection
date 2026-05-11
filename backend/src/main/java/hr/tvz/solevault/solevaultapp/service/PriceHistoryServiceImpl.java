package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.PriceHistory;
import hr.tvz.solevault.solevaultapp.model.PriceHistoryCommand;
import hr.tvz.solevault.solevaultapp.model.PriceHistoryDTO;
import hr.tvz.solevault.solevaultapp.model.Sneaker;
import hr.tvz.solevault.solevaultapp.repository.PriceHistoryJpaRepository;
import hr.tvz.solevault.solevaultapp.repository.SneakerJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceHistoryServiceImpl implements PriceHistoryService {
    private PriceHistoryJpaRepository repository;
    private SneakerJpaRepository sneakerJpaRepository;

    @Override
    public List<PriceHistoryDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public Optional<PriceHistoryDTO> findById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    @Override
    public List<PriceHistoryDTO> findBySneakerId(Long sneakerId) {
        return repository.findBySneakerId(sneakerId).stream().map(this::toDTO).toList();
    }

    @Override
    public PriceHistoryDTO addPriceHistory(PriceHistoryCommand command) {
        Sneaker sneaker = sneakerJpaRepository.findById(command.getSneakerId()).orElseThrow(() -> new RuntimeException("Sneaker not found with id: " + command.getSneakerId()));
        PriceHistory newPriceHistory = new PriceHistory(null, sneaker, command.getPrice(), LocalDateTime.now(), command.getSource());
        return toDTO(repository.save(newPriceHistory));
    }

    @Override
    public Optional<PriceHistoryDTO> updatePriceHistory(Long id, PriceHistoryCommand command) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }
        Sneaker sneaker = sneakerJpaRepository.findById(command.getSneakerId()).orElseThrow(() -> new RuntimeException("Sneaker not found with id: " + command.getSneakerId()));
        PriceHistory updatedPriceHistory = new PriceHistory(id, sneaker, command.getPrice(), LocalDateTime.now(), command.getSource());
        return Optional.of(toDTO(repository.save(updatedPriceHistory)));
    }

    @Override
    public boolean deletePriceHistory(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private PriceHistoryDTO toDTO(PriceHistory ph) {
        return new PriceHistoryDTO(ph.getId(), ph.getSneaker().getId(), ph.getPrice(), ph.getRecordedAt(), ph.getSource());
    }
}
