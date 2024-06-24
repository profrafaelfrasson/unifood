package br.com.unifood.unifood.model.dto;

import br.com.unifood.unifood.model.category.Categories;

import java.time.LocalDateTime;

public record ProductsDTO(String product_code, String name, double cost_value, double purchase_value, Categories categories, LocalDateTime created_at,
                          LocalDateTime updated_at, String description) {

}
