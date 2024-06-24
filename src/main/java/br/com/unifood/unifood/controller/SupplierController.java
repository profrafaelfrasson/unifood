package br.com.unifood.unifood.controller;

import br.com.unifood.unifood.model.address.Address;
import br.com.unifood.unifood.model.dto.SupplierDTO;
import br.com.unifood.unifood.model.suppliers.Suppliers;
import br.com.unifood.unifood.repository.AddressRepository;
import br.com.unifood.unifood.service.SupplierService;
import br.com.unifood.unifood.utils.CnpjUtils;
import br.com.unifood.unifood.utils.ZipCodeUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/")
    public List<Suppliers> getSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable int id) {
        try {
            Suppliers supplier = supplierService.getById(id);
            return ResponseEntity.ok(supplier);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        try {
            Suppliers savedSupplier = supplierService.save(supplierDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable int id, @RequestBody SupplierDTO supplierDTO) {
        try {
            if (id != supplierDTO.id()) {
                throw new RuntimeException("Id not valid");
            }
            Suppliers updatedSupplier = supplierService.save(supplierDTO);
            return ResponseEntity.ok(updatedSupplier);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable int id) {
        try {
            supplierService.deleteBydId(id);
            return ResponseEntity.ok().body("Deleted with success");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}