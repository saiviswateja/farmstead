package com.viswateja.farmstead.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductFeedTest {

    @Autowired
    public ProductFeed productFeed;

    @Test
    void run() throws UnsupportedEncodingException, FileNotFoundException {
        productFeed.run();
    }
}