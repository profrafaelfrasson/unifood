package br.com.unifood.unifood.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class UniRepository<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>, JpaSpecificationExecutor<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    protected UniRepository(Class<T> entityClass, EntityManager entityManager) {
        super(entityClass, entityManager);
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    @Override
    public Page<T> search(String search, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        List<Predicate> predicates = new ArrayList<>();
        if (search != null && !search.trim().isEmpty()) {
            String searchLower = "%" + search.toLowerCase() + "%";
            for (String field : getFieldsSearchable()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(field)),
                        searchLower
                ));
            }
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<T> results = query.getResultList();
        long total = count(search);

        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public abstract List<String> getFieldsSearchable();

    private long count(String search) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);

        List<Predicate> predicates = new ArrayList<>();
        if (search != null && !search.trim().isEmpty()) {
            String searchLower = "%" + search.toLowerCase() + "%";
            for (String field : getFieldsSearchable()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(field)),
                        searchLower
                ));
            }
        }

        criteriaQuery.select(criteriaBuilder.count(root))
                .where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

}
