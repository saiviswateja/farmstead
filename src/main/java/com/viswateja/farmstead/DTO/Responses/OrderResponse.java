package com.viswateja.farmstead.DTO.Responses;

import com.viswateja.farmstead.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private String orderId;

    private Integer quantity;

    private Integer status;

    private String productTitle;

    private Integer msrp;

    private Address shippingAddress;

    private String emailAddress;

    private String shipPhoneNumber;
}
