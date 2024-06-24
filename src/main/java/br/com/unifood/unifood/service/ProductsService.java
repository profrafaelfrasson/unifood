package br.com.unifood.unifood.service;

import br.com.unifood.unifood.model.products.Products;
import br.com.unifood.unifood.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

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
