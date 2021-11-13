package com.viswateja.farmstead.service;

import com.viswateja.farmstead.Repository.OrderLineItemRepository;
import com.viswateja.farmstead.entity.OrderLineItem;
import com.viswateja.farmstead.entity.OrderSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderLineItemService {

    @Autowired
    public OrderLineItemRepository orderLineItemRepository;

    public OrderLineItem saveOrderLineItem(OrderLineItem orderLineitem) {
        return orderLineItemRepository.save(orderLineitem);
    }

    public List<OrderLineItem> retrieveOrderLineItemsByOrdersummaryFk(OrderSummary orderSummary) {
        return orderLineItemRepository.getOrdersByOrderId(orderSummary.getOrderSummaryPk());
    }

    public void shipOrders() {
        System.out.println("Shipping orders" + new Date().getTime());
        orderLineItemRepository.shipOrders();
    }
}
