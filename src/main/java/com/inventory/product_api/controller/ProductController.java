

package com.inventory.product_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.inventory.product_api.model.Product;
import com.inventory.product_api.service.ProductService;
import com.inventory.product_api.util.JwtUtil;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product API", description = "Operations related to products")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
//	private static final String VALID_TOKEN = "mysecrettoken123";

    private final ProductService service;

    @PostMapping
    @Operation(
            summary = "Create new product",
            description = "Add a new product to the inventory")
    public Product addProduct(@Valid @RequestBody Product product) {
    	
        return service.save(product);
    	
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Fetch list of all products")
    public List<Product> getAllProducts() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get product by ID",
            description = "Fetch a single product using its ID")
    public Product getProduct(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete product",
            description = "Remove a product from the inventory by ID")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    	boolean isDeleted = service.delete(id);

        if (isDeleted) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Product not found");
        }
    }
    
    @PutMapping("/{id}")
    @Operation(
            summary = "Update product (Full)",
            description = "Replace all fields of a product")
    public Product updateProduct( @PathVariable Long id, @Valid @RequestBody Product product) {
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
    @Operation(summary = "Partially update product", description = "Update only selected fields")
    public Product patchProduct(@PathVariable Long id, @RequestBody Product product) {
    	  Product existing = service.getById(id);

    	    if (existing == null) {
    	        throw new RuntimeException("Product not found");
    	    }

    	    // 🔹 Name update (null + empty safe)
    	    if (product.getName() != null && !product.getName().trim().isEmpty()) {
    	        existing.setName(product.getName());
    	    }

    	    // 🔹 Price update (null safe)
    	    if (product.getPrice() != null) {
    	        existing.setPrice(product.getPrice());
    	    }

    	    // 🔹 Quantity update (null safe)
    	    if (product.getQuantity() != null) {
    	        existing.setQuantity(product.getQuantity());
    	    }

    	    return service.save(existing);
    }
    
    @GetMapping("/headers")
    public String getHeaders(
            @RequestHeader("client") String client,
            @RequestHeader("version") String version,
            @RequestHeader(value = "Authorization", required = false) String token) {
    	System.out.println("TOKEN RECEIVED: [" + token + "]");
    	 try {
        if (token == null || !token.startsWith("Bearer ")) {
            return "Unauthorized";
        }

        String jwt = token.substring(7);
        String user = JwtUtil.validateToken(jwt);
        
        return "Client: " + client + ", Version: " + version + ", User: " + user;
    	 } catch (Exception e) {
             return "Invalid or Expired Token";
         }
       
    }
    
    

   
    
    public ProductController(ProductService service) {
        this.service = service;
    }
}