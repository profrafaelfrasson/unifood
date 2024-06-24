package br.com.unifood.unifood.repository;

import br.com.unifood.unifood.model.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    UserDetails findByEmail(String email);


}