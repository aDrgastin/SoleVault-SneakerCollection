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

    @PostMapping
    public ResponseEntity<?> addBrand(@Valid @RequestBody BrandCommand brandCommand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField, FieldError::getDefaultMessage, (existing, duplicate) -> existing + "\n" + duplicate
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        return brandService.add(brandCommand)
                .map(dto -> ResponseEntity.status(201).body(dto))
                .orElse(ResponseEntity.status(409).build());
    }
}
