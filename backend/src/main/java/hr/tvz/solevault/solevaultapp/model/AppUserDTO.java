package hr.tvz.solevault.solevaultapp.model;

import java.util.List;

public record AppUserDTO(
        Long id,
        String username,
        String firstName,
        String lastName,
        List<String> roles
) { }
