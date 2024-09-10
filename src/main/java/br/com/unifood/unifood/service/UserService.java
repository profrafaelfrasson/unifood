package br.com.unifood.unifood.service;

import br.com.unifood.unifood.model.category.Categories;
import br.com.unifood.unifood.model.users.Users;
import br.com.unifood.unifood.repository.CategoryRepository;
import br.com.unifood.unifood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Page<Categories> index(String search, Pageable pageable) {
        return repository.search(search, pageable);
    }
//    public List<Users> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public Optional<Users> getUserById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    public void deleteUserById(Long id) {
//        userRepository.deleteById(id);
//    }

}
