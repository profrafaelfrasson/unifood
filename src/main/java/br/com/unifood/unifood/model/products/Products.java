package br.com.unifood.unifood.model.products;

import br.com.unifood.unifood.model.category.Categories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String product_code;
    private String name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    public Products(String productCode, String name, Categories category, String description, LocalDateTime updated_at) {
        this.product_code = productCode;
        this.name = name;
        this.category = category;
        this.description = description;
        this.updated_at = updated_at;
    }

    public Products(String productCode, String name, Categories category, String description) {
        this.product_code = productCode;
        this.name = name;
        this.category = category;
        this.description = description;
    }


}
