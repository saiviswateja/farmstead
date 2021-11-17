package com.viswateja.farmstead.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    public ProductRepository productRepository;

    @Test
    public void updateStatusBySkuTest() {
        productRepository.updateStatusBySku("123", 1);
    }
}