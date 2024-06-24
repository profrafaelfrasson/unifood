package br.com.unifood.unifood.repository;

import br.com.unifood.unifood.model.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    Products findByName(String name);
}
