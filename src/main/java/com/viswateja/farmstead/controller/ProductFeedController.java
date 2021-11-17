package com.viswateja.farmstead.controller;

import com.viswateja.farmstead.service.ProductFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductFeedController {
    @Autowired
    private ProductFeed productFeed;

    @GetMapping("/productfeed")
    public void runProductFeed() {
        productFeed.run();
    }
}
