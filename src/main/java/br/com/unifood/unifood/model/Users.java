package br.com.unifood.unifood.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String email;
    private String name;

}