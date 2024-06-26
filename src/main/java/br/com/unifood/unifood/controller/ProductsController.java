package br.com.unifood.unifood.controller;


import br.com.unifood.unifood.model.dto.ProductsDTO;
import br.com.unifood.unifood.model.products.Products;
import br.com.unifood.unifood.repository.CategoryRepository;
import br.com.unifood.unifood.repository.ProductsRepository;
import br.com.unifood.unifood.service.AuthorizationService;
import br.com.unifood.unifood.service.ProductsService;
import br.com.unifood.unifood.utils.GlobalError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private CategoryRepository categoryRepository;

    GlobalError GlobalError = new GlobalError();

    @PostMapping("/products")
    public ResponseEntity register(@RequestBody @Validated ProductsDTO data) {
        try {
            if (this.productsRepository.findByName(data.name()) != null) return ResponseEntity.badRequest().build();
            AuthorizationService auth = new AuthorizationService();                              //to do
            Products newProducts = new Products(data.product_code(),data.name(),data.cost_value(),data.categories(), data.purchase_value(), auth.createdDateLocalNow(), null, data.description());
            this.productsRepository.save(newProducts);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProducts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(GlobalError.createErrorResponse(e.getMessage(), "Não foi possivel registrar"));
        }
    }
    @GetMapping("/")
    public List<Products> getAllProducts(){
        List<Products> products = productsService.getAllCategories();
        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        Optional<Products> listProducts = productsService.getProductById(id);
        if(listProducts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listProducts);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Validated ProductsDTO data) {
        Products existingCategory = productsRepository.findById(id).orElse(null);
        if (existingCategory == null) {
            return ResponseEntity.notFound().build();
        }
        LocalDateTime originalCreatedAt = existingCategory.getCreated_at();
        if (data.name() != null) {
            existingCategory.setName(data.name());
        }
        if (data.description() != null) {
            existingCategory.setDescription(data.description());
        }
        existingCategory.setCreated_at(originalCreatedAt);
        existingCategory.setUpdated_at(LocalDateTime.now());
        productsRepository.save(existingCategory);

        return ResponseEntity.ok(productsRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Optional<Products> products = productsService.getProductById(id);
        if(products.isEmpty()) {
            return new ResponseEntity<>("Categoria não encontrado!", HttpStatus.NOT_FOUND);
        } else {
            productsService.deleteProductById(id);
            String names = products.stream()
                    .map(Products::getName)
                    .collect(Collectors.joining());

            return new ResponseEntity<>(": " + names + " deletado com sucesso!",HttpStatus.NO_CONTENT);
        }
    }


}
