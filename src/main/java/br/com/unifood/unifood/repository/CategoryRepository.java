package br.com.unifood.unifood.repository;

import br.com.unifood.unifood.model.category.Categories;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository extends UniRepository<Categories, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public CategoryRepository(EntityManager entityManager) {
        super(Categories.class, entityManager);
    }

    @Override
    public List<String> getFieldsSearchable() {
        return List.of("name", "description"); // Fields to be searchable for Categories
    }
}
