package com.viswateja.farmstead.service;

import com.viswateja.farmstead.DTO.Requests.OrderRequest;
import com.viswateja.farmstead.DTO.Responses.OrderResponse;
import com.viswateja.farmstead.entity.OrderLineItem;
import com.viswateja.farmstead.entity.OrderSummary;
import com.viswateja.farmstead.entity.Product;
import com.viswateja.farmstead.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
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

        long currentTimestamp = Instant.now().toEpochMilli();
        String orderId = user.get().getUsername().substring(0,3).toUpperCase() + "-" + currentTimestamp;

        OrderSummary orderSummary = OrderSummary.builder()
                .address(orderRequest.getAddress())
                .emailAddress(orderRequest.getEmailAddress())
                .shipPhoneNumber(orderRequest.getShipPhoneNumber())
                .user(user.get())
                .orderId(orderId)
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

    public OrderResponse getOrderDetailsByOrderId(String orderId) {
        OrderSummary orderSummary = orderSummaryService.retrieveOrdersummaryByOrderId(orderId);
        OrderLineItem orderLineItem = orderLineItemService.retrieveOrderLineItemsByOrdersummaryFk(orderSummary).get(0);
        OrderResponse orderResponse = OrderResponse.builder()
                .emailAddress(orderSummary.getEmailAddress())
                .shippingAddress(orderSummary.getAddress())
                .status(orderLineItem.getStatus())
                .productTitle(orderLineItem.getProduct().getName())
                .msrp(orderLineItem.getProduct().getMsrp())
                .quantity(orderLineItem.getQuantity())
                .orderId(orderId)
                .shipPhoneNumber(orderSummary.getShipPhoneNumber())
                .build();
        return orderResponse;
    }
}
