package br.com.unifood.unifood.controller;

import br.com.unifood.unifood.model.users.Users;
import br.com.unifood.unifood.model.dto.AuthenticationDTO;
import br.com.unifood.unifood.model.dto.LoginResponseDTO;
import br.com.unifood.unifood.model.dto.RegisterDTO;
import br.com.unifood.unifood.repository.UserRepository;
import br.com.unifood.unifood.security.TokenService;
import br.com.unifood.unifood.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        AuthorizationService auth = new AuthorizationService();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Users newUser = new Users(data.email(), encryptedPassword, data.name(), auth.createdDateLocalNow());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
