package br.com.unifood.unifood.model.dto;

import java.time.LocalDateTime;

public record CategoryDTO(String name, String description, LocalDateTime created_at, LocalDateTime update_at) {
}
