package br.com.unifood.unifood.repository;

import br.com.unifood.unifood.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    // métodos personalizados, se necessário
}