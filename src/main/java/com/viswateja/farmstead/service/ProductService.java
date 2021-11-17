package com.viswateja.farmstead.service;

import com.viswateja.farmstead.Repository.ProductRepository;
import com.viswateja.farmstead.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> retrieveProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> retrieveProductById(Long id) {
        return productRepository.findById(id);
    }

    public void updateProductStatusBySku(String sku, Integer status) {
        productRepository.updateStatusBySku(sku, status);
    }
}
