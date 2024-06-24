package br.com.unifood.unifood.repository;

import br.com.unifood.unifood.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {


}