package br.com.unifood.unifood.model.address;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;
    private String number;
    private String complement;
    private String postal_code;

    public Address() {
        // Default constructor is needed by Hibernate
    }

    public Address(String street, String number, String complement, String postal_code) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.postal_code = postal_code;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getPostalCode() {
        return postal_code;
    }

    public void setPostalCode(String postal_code) {
        this.postal_code = postal_code;
    }
}
