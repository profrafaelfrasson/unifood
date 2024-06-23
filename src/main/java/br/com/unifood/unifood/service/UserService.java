package br.com.unifood.unifood.service;

import br.com.unifood.unifood.model.users.Users;
import br.com.unifood.unifood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Users saveOrUpdateUser(Users users) {
        return userRepository.save(users);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
