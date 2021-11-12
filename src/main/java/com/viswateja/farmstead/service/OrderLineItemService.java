package com.viswateja.farmstead.service;

import com.viswateja.farmstead.Repository.OrderLineItemRepository;
import com.viswateja.farmstead.entity.OrderLineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineItemService {

    @Autowired
    public OrderLineItemRepository orderLineItemRepository;

    public OrderLineItem saveOrderLineItem(OrderLineItem orderLineitem) {
        return orderLineItemRepository.save(orderLineitem);
    }
}
