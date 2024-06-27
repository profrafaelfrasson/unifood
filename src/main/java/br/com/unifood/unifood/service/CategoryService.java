package br.com.unifood.unifood.service;

import br.com.unifood.unifood.model.category.Categories;
import br.com.unifood.unifood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryRepository repository;

    public Categories registerProduct(String name, String desc, LocalDateTime H_now) {
        if (this.repository.findByName(name) != null) return null;
        AuthorizationService auth = new AuthorizationService();
        Categories newCategories = new Categories(name, desc, H_now, null);
        this.repository.save(newCategories);
        return newCategories;
    }

    public Optional<Categories> updateProduct(Long id, String name, String desc) {
        Categories existCategory = repository.findById(id).orElse(null);
        if (existCategory == null) {
            return Optional.empty();
        }
        LocalDateTime originalCreatedAt = existCategory.getCreated_at();
        if (name.isEmpty()) {existCategory.setName(name);}
        if (desc.isEmpty()) {existCategory.setDescription(desc);}
        existCategory.setCreated_at(originalCreatedAt);
        existCategory.setUpdated_at(LocalDateTime.now());
        repository.save(existCategory);
        return Optional.of(existCategory); //sla
    }


    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Categories findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public boolean deleteCategoryById(Long id) {
        Categories categories = findById(id);
        categoryRepository.deleteById(id);
        return true;
    }

}
