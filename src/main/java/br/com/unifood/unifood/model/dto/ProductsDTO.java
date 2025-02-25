package br.com.unifood.unifood.model.dto;

import java.time.LocalDateTime;

public record ProductsDTO(
        Long category_id,
        String name,
        String description,
        String product_code,
        LocalDateTime created_at,
        LocalDateTime updated_at
    ) {
}
