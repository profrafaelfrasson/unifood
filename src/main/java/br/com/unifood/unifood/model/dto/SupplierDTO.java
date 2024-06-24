package br.com.unifood.unifood.model.dto;

import java.time.LocalDateTime;

public record SupplierDTO(int id, String name, String company_name,  String email, String cnpj, String telephone, AddressDTO address) {
}

