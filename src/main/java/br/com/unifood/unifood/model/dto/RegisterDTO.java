package br.com.unifood.unifood.model.dto;

import java.time.LocalDateTime;

public record RegisterDTO(String email, String password, String name, LocalDateTime created_at) {
}

