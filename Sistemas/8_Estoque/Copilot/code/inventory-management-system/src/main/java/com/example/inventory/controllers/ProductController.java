package com.example.inventory.controllers;

import com.example.inventory.models.Product;
import com.example.inventory.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> registerProduct(@RequestBody Product product) {
        Product createdProduct = productService.registerProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/entry")
    public ResponseEntity<Void> addProductEntry(@PathVariable Long id, @RequestParam int quantity) {
        productService.addProductEntry(id, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/exit")
    public ResponseEntity<Void> addProductExit(@PathVariable Long id, @RequestParam int quantity) {
        productService.addProductExit(id, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferProduct(@RequestParam Long productId, @RequestParam Long fromWarehouseId, @RequestParam Long toWarehouseId, @RequestParam int quantity) {
        productService.transferProduct(productId, fromWarehouseId, toWarehouseId, quantity);
        return ResponseEntity.ok().build();
    }
}