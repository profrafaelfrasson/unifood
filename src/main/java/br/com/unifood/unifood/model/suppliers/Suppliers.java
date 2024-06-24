package br.com.unifood.unifood.model.suppliers;

import br.com.unifood.unifood.model.address.Address;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "suppliers")
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String company_name;
    private String email;
    @CNPJ
    private String cnpj;
    private String telephone;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public Suppliers() {
        // Default constructor is needed by Hibernate
    }

    public Suppliers(String name, String company_name, String email, String cnpj, String telephone, Address address) {
        this.name = name;
        this.email = email;
        this.cnpj = cnpj;
        this.telephone = telephone;
        this.company_name = company_name;
        this.address = address;
    }

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return company_name;
    }

    public String getEmail() {
        return email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelephone() {
        return telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}