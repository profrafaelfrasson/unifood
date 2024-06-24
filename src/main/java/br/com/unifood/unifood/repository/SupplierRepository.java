package br.com.unifood.unifood.repository;

import br.com.unifood.unifood.model.suppliers.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Suppliers, Integer> {
    Suppliers findByCnpj(String cnpj);
}