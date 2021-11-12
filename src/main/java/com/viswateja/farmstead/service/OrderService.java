package com.viswateja.farmstead.service;

import com.viswateja.farmstead.DTO.Requests.OrderRequest;
import com.viswateja.farmstead.entity.OrderLineItem;
import com.viswateja.farmstead.entity.OrderSummary;
import com.viswateja.farmstead.entity.Product;
import com.viswateja.farmstead.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    public OrderSummaryService orderSummaryService;

    @Autowired
    public OrderLineItemService orderLineItemService;

    @Autowired
    public UserService userService;

    @Autowired
    public ProductService productService;

    public OrderLineItem saveOrder(OrderRequest orderRequest) throws Exception {
        Optional<User> user = userService.getUserByID(orderRequest.getUserFk());
        if(!user.isPresent()) {
            throw new Exception("User not found");
        }

        OrderSummary orderSummary = OrderSummary.builder()
                .address(orderRequest.getAddress())
                .emailAddress(orderRequest.getEmailAddress())
                .shipPhoneNumber(orderRequest.getShipPhoneNumber())
                .user(user.get())
                .build();

        OrderSummary orderSummarySaved = orderSummaryService.addOrderSummary(orderSummary);

        Optional<Product> product = productService.retrieveProductById(orderRequest.getProductFk());
        if(!product.isPresent()) {
            throw new Exception("product ordered is currently not available");
        }

        OrderLineItem orderLineItem = OrderLineItem
                .builder()
                .orderSummaryFk(orderSummarySaved)
                .product(product.get())
                .quantity(orderRequest.getQuantity())
                .status(0)
                .build();

        return orderLineItemService.saveOrderLineItem(orderLineItem);
    }
}
