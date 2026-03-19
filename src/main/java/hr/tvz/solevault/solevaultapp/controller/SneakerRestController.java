package hr.tvz.solevault.solevaultapp.controller;

import hr.tvz.solevault.solevaultapp.controller.service.SneakerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sneakers")
@AllArgsConstructor
public class SneakerRestController {
    private final SneakerService sneakerService;
}
