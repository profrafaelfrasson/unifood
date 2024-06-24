package br.com.unifood.unifood.model.products;

import br.com.unifood.unifood.model.category.Categories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Set;

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
    private double cost_value;
    private double purchase_value;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories; //FK

    public Products(String product_code, String name, double cost_value, Categories categories, double purchase_value, LocalDateTime created_at, LocalDateTime updated_at, String description) {
        this.product_code = product_code;
        this.name = name;
        this.cost_value = cost_value;
        this.purchase_value = purchase_value;
        this.categories = categories;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
