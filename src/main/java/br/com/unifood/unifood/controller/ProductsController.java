package br.com.unifood.unifood.controller;

import br.com.unifood.unifood.model.dto.ProductsDTO;
import br.com.unifood.unifood.model.products.Products;
import br.com.unifood.unifood.repository.CategoryRepository;
import br.com.unifood.unifood.repository.ProductsRepository;
import br.com.unifood.unifood.service.AuthorizationService;
import br.com.unifood.unifood.service.CategoryService;
import br.com.unifood.unifood.service.ProductsService;
import br.com.unifood.unifood.utils.GlobalError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("api/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorizationService authSer;

    GlobalError globalError = new GlobalError();

    @GetMapping("/")
    public Page<Products> getAllProducts(Pageable pageable){
        return productsService.getAllProducts(pageable);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
//        try {
//            Optional<Products> listProducts = productsService.getProductById(id);
//            if(listProducts.isEmpty()) {
//                return ResponseEntity.notFound().build();
//            }
//            return ResponseEntity.ok(listProducts);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(globalError.createErrorResponse(e.getMessage(), "Não foi possivel listar os produtos"));
//        }
//    }
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody @Validated ProductsDTO data) {
        try {
            Products productsRegister = productsService.registerProduct(data.product_code(), data.name(), data.category_id(), data.description());
            return ResponseEntity.status(HttpStatus.CREATED).body(productsRegister);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalError.createErrorResponse(e.getMessage(),"Erro ao Registrar Produto!"));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Validated ProductsDTO data) {
        try {
            Products product = productsService.update(id, data.name(), data.description(), data.category_id());
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(globalError.createErrorResponse(e.getMessage(), "Não foi possivel atualizar"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        try {
            productsService.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body((Products) globalError.createErrorResponse(e.getMessage(), "Não foi possivel deletar"));
        }
    }

}
