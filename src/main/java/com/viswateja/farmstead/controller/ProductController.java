package com.viswateja.farmstead.controller;

import com.viswateja.farmstead.entity.Product;
import com.viswateja.farmstead.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/product")
    public List<Product> getProducts() {
        return productService.retrieveProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long productId) throws Exception {
        Optional<Product> product = productService.retrieveProductById(productId);
        if (!product.isPresent()) {
            throw new Exception("product not found");
        }
        return product.get();
    }
}
