package br.com.unifood.unifood.service;

import br.com.unifood.unifood.model.category.Categories;
import br.com.unifood.unifood.model.products.Products;
import br.com.unifood.unifood.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CategoryService categoryService;


    public Products registerProduct(String productCode, String name, Long categoryId, String description) {
//        AuthorizationService auth = new AuthorizationService();

        Categories category = this.categoryService.findById(categoryId);
        Products newProducts = new Products(productCode, name, category, description);

        this.productsRepository.save(newProducts);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProducts).getBody();
    }

    public Products update(Long id, String name, String desc, Long categoryId) {
        Products product = getProductById(id).orElseThrow();

        if(categoryId != null) {
            Categories category = this.categoryService.findById(categoryId);
            product.setCategory(category);
        }

        product.setName(name);
        product.setDescription(desc);
        product.setUpdated_at(LocalDateTime.now());

        productsRepository.save(product);
        return product;
    }

    public Page<Products> getAllProducts(Pageable pageable) {
        return productsRepository.findByDeletedAtIsNull(pageable);
    }

    public Optional<Products> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteProductById(Long id) {
        Products product = getProductById(id).orElseThrow();
        product.setDeletedAt(LocalDateTime.now());
        productsRepository.save(product);
    }
}
