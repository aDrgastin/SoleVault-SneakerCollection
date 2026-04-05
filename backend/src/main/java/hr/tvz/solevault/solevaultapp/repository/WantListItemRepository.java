package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.WantListItem;

import java.util.List;
import java.util.Optional;

public interface WantListItemRepository {
    List<WantListItem> findAll();

    Optional<WantListItem> findById(Long id);

    Optional<WantListItem> addWantItem(WantListItem wantListItem);
}
