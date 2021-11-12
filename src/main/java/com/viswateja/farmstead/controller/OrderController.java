package com.viswateja.farmstead.controller;

import com.viswateja.farmstead.DTO.Requests.OrderRequest;
import com.viswateja.farmstead.entity.*;
import com.viswateja.farmstead.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    public OrderService orderService;

    @PostMapping("/ordersummary")
    public OrderLineItem addOrderSummary(@RequestBody OrderRequest orderRequest) throws Exception {
        return orderService.saveOrder(orderRequest);
    }
}
