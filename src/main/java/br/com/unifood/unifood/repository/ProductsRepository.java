package br.com.unifood.unifood.repository;

import br.com.unifood.unifood.model.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    Products findByName(String name);

    Page<Products> findByDeletedAtIsNull(Pageable pageable);
}
