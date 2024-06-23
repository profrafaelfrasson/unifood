package br.com.unifood.unifood.service;

import br.com.unifood.unifood.model.category.Categories;
import br.com.unifood.unifood.model.users.Users;
import br.com.unifood.unifood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Categories> getUserById(Long id) {
        return categoryRepository.findById(id);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

}
