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
//
//    @PostMapping("/")
//    public ResponseEntity<Object> register(@RequestBody @Validated ProductsDTO data) {
//        try {
//            Products productsRegister = productsService.registerProduct(data.product_code(),data.name(),data.cost_value(),
//                    data.category_id(), data.purchase_value(),authSer.createdDateLocalNow(),data.description());
//            if(productsRegister != null){
//                return ResponseEntity.status(HttpStatus.CREATED).body(productsRegister);
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto já cadastrado!");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalError.createErrorResponse(
//                    e.getMessage(),"Erro ao Registrar Produto!"));
//        }
//    }
//    @GetMapping("/all")
//    public List<Products> getAllProducts(){
//        return productsService.getAllCategories();
//    }
//
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
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Validated ProductsDTO data) {
//        try {
//            productsService.updateProduct(id, data.name(), data.description(),data.cost_value(), data.purchase_value(), data.category_id());
//            return ResponseEntity.ok(productsRepository.findById(id));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(globalError.createErrorResponse(e.getMessage(), "Não foi possivel atualizar"));
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
//        try {
//            Optional<Products> products = productsService.getProductById(id);
//            if(products.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            } else {
//                productsService.deleteProductById(id);
//
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body((Products) globalError.createErrorResponse(e.getMessage(), "Não foi possivel atualizar"));
//        }
//    }


}
