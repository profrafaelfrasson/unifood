package br.com.unifood.unifood.service;

import br.com.unifood.unifood.model.category.Categories;
import br.com.unifood.unifood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Categories registerProduct(String name, String desc, LocalDateTime H_now) {
        if (this.repository.findByName(name) != null) return null;
        AuthorizationService auth = new AuthorizationService();
        Categories newCategories = new Categories(name, desc, H_now, null);
        this.repository.save(newCategories);
        return newCategories;
    }

    public Categories updateCategory(Long id, String name, String description) {
        Categories category = repository.findById(id).orElse(null);

        if (category == null) {
            return null;
        }

        if (!name.isEmpty()) {category.setName(name);}
        if (!description.isEmpty()) {category.setDescription(description);}

        repository.save(category);
        return category;
    }


    public List<Categories> getAllCategories() {
        return repository.findAll();
    }

    public Categories findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public boolean deleteCategoryById(Long id) {
        Categories categories = findById(id);
        repository.deleteById(id);
        return true;
    }

}
