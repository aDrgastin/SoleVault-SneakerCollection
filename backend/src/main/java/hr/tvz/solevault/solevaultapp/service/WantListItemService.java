package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.WantListItemCommand;
import hr.tvz.solevault.solevaultapp.model.WantListItemDTO;

import java.util.List;
import java.util.Optional;

public interface WantListItemService {
    List<WantListItemDTO> findAll();

    Optional<WantListItemDTO> findById(Long id);

    Optional<WantListItemDTO> addWantItem(WantListItemCommand wantItemCommand);
}
