package com.viswateja.farmstead.DTO.Requests;

import com.viswateja.farmstead.entity.Address;
import com.viswateja.farmstead.entity.OrderSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Long productFk;

    private Long userFk;

    private Integer quantity;

    private Address address;

    private String shipPhoneNumber;

    private String emailAddress;
}
