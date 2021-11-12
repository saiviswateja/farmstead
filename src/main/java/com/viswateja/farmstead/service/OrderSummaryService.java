package com.viswateja.farmstead.service;

import com.viswateja.farmstead.Repository.OrderSummaryRepository;
import com.viswateja.farmstead.entity.OrderSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSummaryService {

    @Autowired
    public OrderSummaryRepository orderSummaryRepository;

    public OrderSummary addOrderSummary(OrderSummary orderSummary) {
        return orderSummaryRepository.save(orderSummary);
    }

    public List<OrderSummary> retrieveOrderSummaries() {
        return orderSummaryRepository.findAll();
    }
}
