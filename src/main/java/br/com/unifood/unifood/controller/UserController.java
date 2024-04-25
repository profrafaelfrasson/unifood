package br.com.unifood.unifood.controller;

import br.com.unifood.unifood.model.AuthenticationDTO;
import br.com.unifood.unifood.model.Users;
import br.com.unifood.unifood.security.TokenService;
import br.com.unifood.unifood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/all")
    public List<Users> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        System.out.println(users);
        return users;
    }

    @GetMapping("/{id}")
    public Optional<Users> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public Users createUser(@RequestBody Users users) {
        return userService.saveOrUpdateUser(users);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    /*** @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Users login) {
        Optional<Users> user = userService.authenticate(login);
        if (user.isPresent()) {
            return ResponseEntity.ok("Logado com sucesso" + " " +user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email invalido");
        }
    }
     */

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok().build();
    }
}

