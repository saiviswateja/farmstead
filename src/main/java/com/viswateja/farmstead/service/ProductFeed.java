package com.viswateja.farmstead.service;

import com.opencsv.CSVWriter;
import com.viswateja.farmstead.Repository.ProductRepository;
import com.viswateja.farmstead.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ProductFeed {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

    public void run() throws UnsupportedEncodingException, FileNotFoundException {
        List<Map<String, String>> productsList = storageService.downloadFile("products.csv");

        Iterator<Map<String, String>> productIterator = productsList.iterator();
        while(productIterator.hasNext()) {
            Map<String, String> product = productIterator.next();
            productService.addProduct(
                    Product.builder()
                            .cost(Integer.parseInt(product.get("cost")))
                            .msrp(Integer.parseInt(product.get("msrp")))
                            .name(product.get("name"))
                            .status(0)
                            .sku(product.get("sku"))
                            .softwarePrice(Integer.parseInt(product.get("msrp")))
                            .build()
            );
        }
    }
}
