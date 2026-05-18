package hr.tvz.solevault.solevaultapp.security;

import hr.tvz.solevault.solevaultapp.model.AppUserDTO;

public record JwtResponse(String accessToken, AppUserDTO user) { }
