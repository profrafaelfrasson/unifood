package br.com.unifood.unifood.service;

import br.com.unifood.unifood.model.category.Categories;
import br.com.unifood.unifood.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    public Page<Categories> index(String search, Pageable pageable) {
        return repository.search(search, pageable);
    }

//    public Categories registerProduct(String name, String desc, LocalDateTime H_now) {
//        if (this.repository.findByName(name) != null) return null;
//        AuthorizationService auth = new AuthorizationService();
//        Categories newCategories = new Categories(name, desc, H_now, null);
//        this.repository.save(newCategories);
//        return newCategories;
//    }
//
//    public Categories updateProduct(Long id, String name, String desc) {
//        Categories existCategory = repository.findById(id).orElse(null);
//        if (existCategory == null) {
//            return null;
//        }
//        LocalDateTime originalCreatedAt = existCategory.getCreated_at();
//        if (name.isEmpty()) {existCategory.setName(name);}
//        if (desc.isEmpty()) {existCategory.setDescription(desc);}
//        existCategory.setCreated_at(originalCreatedAt);
//        existCategory.setUpdated_at(LocalDateTime.now());
//        repository.save(existCategory);
//        return existCategory; //sla
//    }
//
//
//    public List<Categories> getAllCategories() {
//        return repository.findAll();
//    }
//
//    public Categories findById(Long id) {
//        return repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
//    }
//
//    public boolean deleteCategoryById(Long id) {
//        Categories categories = findById(id);
//        repository.deleteById(id);
//        return true;
//    }

}
