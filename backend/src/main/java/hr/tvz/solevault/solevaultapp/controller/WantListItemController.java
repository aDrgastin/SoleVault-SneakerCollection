package hr.tvz.solevault.solevaultapp.controller;

import hr.tvz.solevault.solevaultapp.model.WantListItemCommand;
import hr.tvz.solevault.solevaultapp.model.WantListItemDTO;
import hr.tvz.solevault.solevaultapp.service.WantListItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wantItems")
@AllArgsConstructor
public class WantListItemController {
    private final WantListItemService wantListItemService;

    @GetMapping
    public ResponseEntity<List<WantListItemDTO>> getWantItems() {
        return ResponseEntity.ok(wantListItemService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addWantItem(@Valid @RequestBody WantListItemCommand wantItemCommand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField, FieldError::getDefaultMessage, (existing, duplicate) -> existing + "\n" + duplicate
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        return wantListItemService.addWantItem(wantItemCommand)
                .map(dto -> ResponseEntity.status(201).body(dto))
                .orElse(ResponseEntity.status(409).build());
    }
}
