

package com.inventory.product_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.inventory.product_api.model.Product;
import com.inventory.product_api.service.ProductService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existing = service.getById(id);

        if (existing != null) {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setQuantity(product.getQuantity());
            return service.save(existing);
        }
        return null;
    }
    
    @PatchMapping("/{id}")
    public Product patchProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existing = service.getById(id);

        if (existing != null) {
            if (product.getName() != null)
                existing.setName(product.getName());

            if (product.getPrice() != 0)
                existing.setPrice(product.getPrice());

            if (product.getQuantity() != 0)
                existing.setQuantity(product.getQuantity());

            return service.save(existing);
        }
        return null;
    }
    
    @GetMapping("/headers")
    public String getHeaders(
            @RequestHeader("client") String client,
            @RequestHeader("version") String version) {

        return "Client: " + client + ", Version: " + version;
    }
    
    public ProductController(ProductService service) {
        this.service = service;
    }
}