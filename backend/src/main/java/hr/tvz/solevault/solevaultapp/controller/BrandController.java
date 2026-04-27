package hr.tvz.solevault.solevaultapp.controller;

import hr.tvz.solevault.solevaultapp.model.BrandCommand;
import hr.tvz.solevault.solevaultapp.model.BrandDTO;
import hr.tvz.solevault.solevaultapp.service.BrandService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getBrands() {
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        return brandService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addBrand(@Valid @RequestBody BrandCommand brandCommand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField, FieldError::getDefaultMessage, (existing, duplicate) -> existing + "\n" + duplicate
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        return brandService.addBrand(brandCommand)
                .map(dto -> ResponseEntity.status(201).body(dto))
                .orElse(ResponseEntity.status(409).build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandCommand brandCommand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField, FieldError::getDefaultMessage, (existing, duplicate) -> existing + "\n" + duplicate
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        return brandService.updateBrand(id, brandCommand)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        if (brandService.deleteBrand(id)) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
}
