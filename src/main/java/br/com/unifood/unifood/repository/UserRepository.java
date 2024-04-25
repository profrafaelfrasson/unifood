package br.com.unifood.unifood.repository;

import org.springframework.data.jpa.repository.Query;
import br.com.unifood.unifood.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

//    @Query("Select us.email,us.password from users us where us.id =;userId")
// String findByEmailAndPassword(@Param("userId"));

    Optional<Users> findByEmailAndPassword(String email, String password);


}