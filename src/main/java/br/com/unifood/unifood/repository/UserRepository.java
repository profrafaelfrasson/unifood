//package br.com.unifood.unifood.repository;
//
//import br.com.unifood.unifood.model.category.Categories;
//import br.com.unifood.unifood.model.users.Users;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//public interface UserRepository extends JpaRepository<Users, Long> {
//
//
//    UserDetails findByEmail(String email);
//
//}

package br.com.unifood.unifood.repository;

import br.com.unifood.unifood.model.category.Categories;
import br.com.unifood.unifood.model.users.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

@Repository
public class UserRepository extends UniRepository<Categories, Long> {
}

@PersistenceContext
private EntityManager entityManager;

public UserRepository(EntityManager entityManager) {
    super(Categories.class, entityManager);
}

@Override
public List<String> getFieldsSearchable() {
    return List.of("name", "description"); // Fields to be searchable for Categories
}

public UserDetails findByEmail(String email)
{
    if (email == null || email.isEmpty()) {
        throw new IllegalArgumentException("Email must not be null or empty");
    }
    TypedQuery<Users> query = entityManager.createQuery(entityManager.getCriteriaBuilder().createQuery(Users.class));

    return query.getResultStream().findFirst().orElse(null);
}
