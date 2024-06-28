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

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CategoryService categoryService;


    public Products registerProduct(String codeP, String name, double costValue, Long category, double purcValue, LocalDateTime h_now, String desc) {
        Categories categories = this.categoryService.findById(category);
        AuthorizationService auth = new AuthorizationService();
        Products newProducts = new Products(codeP, name, costValue, categories, purcValue,h_now,null, desc);
        this.productsRepository.save(newProducts);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProducts).getBody();
    }

    public Products updateProduct(Long id, String name, String desc, double costValue, double purchValue, Long category) {
        Categories categories = this.categoryService.findById(category);
        Products existingProduct = getProductById(id).orElse(null);
        if (existingProduct == null) {
            return null;
        }
        LocalDateTime originalCreatedAt = existingProduct.getCreated_at();
        existingProduct.setCreated_at(originalCreatedAt);
        existingProduct.setName(name);
        existingProduct.setDescription(desc);
        existingProduct.setUpdated_at(LocalDateTime.now());
        existingProduct.setPurchase_value(purchValue);
        existingProduct.setCost_value(costValue);
        existingProduct.setCategories(categories);
        productsRepository.save(existingProduct);
        return existingProduct;
    }

    public List<Products> getAllCategories() {
        return productsRepository.findAll();
    }

    public Optional<Products> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }

}
