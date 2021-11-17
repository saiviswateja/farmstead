package com.viswateja.farmstead.service;

import com.viswateja.farmstead.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductFeed {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

    public void run() {
        HashMap<String, Product> productsFromCloud = new HashMap<>();

        List<Product> productsList = storageService.downloadFile("products.csv");
        List<Product> oldProductsFromDb = productService.retrieveProducts();

        productsList.forEach(product -> {
            productsFromCloud.put(product.getSku(), product);
        });

        List<Product> updatedProducts = new ArrayList<>();
        List<Product> removedProducts = new ArrayList<>();
        List<Product> newProducts = new ArrayList<>();

        oldProductsFromDb.forEach(product -> {
            if (productsFromCloud.containsKey(product.getSku())) {
                Product currentProduct = productsFromCloud.get(product.getSku());
                if (product.getStatus() == 0) {
                    newProducts.add(product);
                } else {
                    if (!product.getName().equals(currentProduct.getName()) || !product.getMsrp().equals(currentProduct.getMsrp())
                            || !product.getCost().equals(currentProduct.getCost())) {
                        updatedProducts.add(product);
                    }
                }
                productsFromCloud.remove(product.getSku());
            } else {
                removedProducts.add(product);
            }
        });

        newProducts.addAll(productsFromCloud.values());

        newProducts.forEach(product -> productService.addProduct(
                Product.builder()
                        .cost(product.getCost())
                        .msrp(product.getMsrp())
                        .name(product.getName())
                        .status(1)
                        .softwarePrice(product.getMsrp())
                        .build()
        ));

        updatedProducts.forEach(product -> {
            productService.updateProductStatusBySku(product.getSku(), 1);
        });

        removedProducts.forEach(product -> {
            productService.updateProductStatusBySku(product.getSku(), 0);
        });
    }
}
