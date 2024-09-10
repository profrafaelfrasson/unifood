package br.com.unifood.unifood.controller;

import br.com.unifood.unifood.model.category.Categories;
import br.com.unifood.unifood.model.users.Users;
import br.com.unifood.unifood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public Page<Categories> index(
            @RequestParam(value = "search", required = false, defaultValue = "") String search, Pageable pageable) {
        return service.index(search, pageable);
    }
//
//    @GetMapping("/all")
//    public List<Users> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Users> getUserById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
//        Optional<Users> user = userService.getUserById(id);
//        if(user.isEmpty()) {
//            return new ResponseEntity<>("Usuário não encontrado!", HttpStatus.NOT_FOUND);
//        } else {
//            userService.deleteUserById(id);
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//    }

}

