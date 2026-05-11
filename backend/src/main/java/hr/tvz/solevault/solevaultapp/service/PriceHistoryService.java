package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.PriceHistoryCommand;
import hr.tvz.solevault.solevaultapp.model.PriceHistoryDTO;

import java.util.List;
import java.util.Optional;

public interface PriceHistoryService {
    List<PriceHistoryDTO> findAll();

    Optional<PriceHistoryDTO> findById(Long id);

    List<PriceHistoryDTO> findBySneakerId(Long sneakerId);

    PriceHistoryDTO addPriceHistory(PriceHistoryCommand priceHistoryCommand);

    Optional<PriceHistoryDTO> updatePriceHistory(Long id, PriceHistoryCommand priceHistoryCommand);

    boolean deletePriceHistory(Long id);
}
