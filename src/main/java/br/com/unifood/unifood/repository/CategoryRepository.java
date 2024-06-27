package br.com.unifood.unifood.repository;

import br.com.unifood.unifood.model.category.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {

    Categories findByName(String name);

}
