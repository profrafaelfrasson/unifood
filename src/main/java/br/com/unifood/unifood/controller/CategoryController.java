package br.com.unifood.unifood.controller;

import br.com.unifood.unifood.model.category.Categories;
import br.com.unifood.unifood.model.dto.CategoryDTO;
import br.com.unifood.unifood.repository.CategoryRepository;
import br.com.unifood.unifood.service.AuthorizationService;
import br.com.unifood.unifood.service.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated CategoryDTO data) {
        try {
            if (this.repository.findByName(data.name()) != null) return ResponseEntity.badRequest().build();
            AuthorizationService auth = new AuthorizationService();
            Categories newCategories = new Categories(data.name(),data.description(), auth.createdDateLocalNow(),null);
            this.repository.save(newCategories);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategories);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/all")
    public List<Categories> getAllCategories() {
        List<Categories> categories = categoryService.getAllCategories();
        return categories;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Optional<Categories> listCategories = categoryService.getUserById(id);
        if(listCategories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listCategories);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody @Validated CategoryDTO data) {
        Categories existingCategory = repository.findById(id).orElse(null);
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
        repository.save(existingCategory);

        return ResponseEntity.ok(repository.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<Categories> categories = categoryService.getUserById(id);
        if(categories.isEmpty()) {
            return new ResponseEntity<>("Categoria não encontrado!", HttpStatus.NOT_FOUND);
        } else {
            categoryService.deleteCategoryById(id);
            String names = categories.stream()
                    .map(Categories::getName)
                    .collect(Collectors.joining());

            return new ResponseEntity<>("Categoria: " + names + " deletado com sucesso!",HttpStatus.OK);
        }
    }


}
