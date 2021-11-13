package com.viswateja.farmstead.helper;

import com.viswateja.farmstead.entity.OrderLineItem;
import com.viswateja.farmstead.service.OrderLineItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderShippingJobTest {

    @Autowired
    private OrderLineItemService orderLineItemService;

    @Test
    public void shipTest() {
        orderLineItemService.shipOrders();
    }
}