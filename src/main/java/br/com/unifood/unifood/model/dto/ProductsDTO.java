package br.com.unifood.unifood.model.dto;

import java.time.LocalDateTime;

public record ProductsDTO(String product_code, String name, double cost_value, double purchase_value, Long category_id, LocalDateTime created_at,
                          LocalDateTime updated_at, String description) {

}
