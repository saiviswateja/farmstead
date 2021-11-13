package com.viswateja.farmstead.controller;

import com.viswateja.farmstead.DTO.Requests.OrderRequest;
import com.viswateja.farmstead.DTO.Responses.OrderResponse;
import com.viswateja.farmstead.entity.*;
import com.viswateja.farmstead.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    public OrderService orderService;

    @PostMapping("/ordersummary")
    public OrderLineItem addOrderSummary(@RequestBody OrderRequest orderRequest) throws Exception {
        return orderService.saveOrder(orderRequest);
    }

    @GetMapping("/order/{orderId}")
    public OrderResponse getOrderByOrderId(@PathVariable("orderId") String orderID) {
        return orderService.getOrderDetailsByOrderId(orderID);
    }
}
