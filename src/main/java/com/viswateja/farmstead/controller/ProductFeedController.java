package com.viswateja.farmstead.controller;

import com.viswateja.farmstead.service.ProductFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductFeedController {
    @Autowired
    private ProductFeed productFeed;

    public void runProductFeed() {
        productFeed.run();g
    }
}
