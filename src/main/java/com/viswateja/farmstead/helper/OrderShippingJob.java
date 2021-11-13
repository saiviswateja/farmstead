package com.viswateja.farmstead.helper;

import com.viswateja.farmstead.service.OrderLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class OrderShippingJob  {

    @Autowired
    private OrderLineItemService orderLineItemService;

    @Scheduled(cron = "0 0 0/6 1/1 * ?")
    public void execute() {
        orderLineItemService.shipOrders();
    }
}
