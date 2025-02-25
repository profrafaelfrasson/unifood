package br.com.unifood.unifood.controller;

import br.com.unifood.unifood.model.category.Categories;
import br.com.unifood.unifood.model.dto.CategoryDTO;
import br.com.unifood.unifood.repository.CategoryRepository;
import br.com.unifood.unifood.service.CategoryService;
import br.com.unifood.unifood.utils.GlobalError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryService categoryService;

    GlobalError globalError = new GlobalError();

    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody @Validated CategoryDTO data) {
         Categories newCategories = categoryService.registerProduct(data.name(), data.description(), data.created_at());

         if (newCategories != null) {
             return ResponseEntity.status(HttpStatus.CREATED).body(newCategories);
         } else {
             return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Categoria já cadastrado");
         }
    }

    @GetMapping("/")
    public List<Categories> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getUserById(@PathVariable Long id) {
        Categories listCategories = categoryService.findById(id);

        return ResponseEntity.ok(listCategories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody @Validated CategoryDTO data) {
        Categories category = categoryService.updateCategory(id, data.name(), data.description());

        if (category != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(category);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Categoria não encontrada!"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        try {
            boolean deleteCategory = categoryService.deleteCategoryById(id);
            if (deleteCategory) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalError.createErrorResponse(
                    e.getMessage(), "Erro ao Deletar Categoria"));
        }
    }
}
