package com.viswateja.farmstead.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSummaryPk;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "userFk",
            referencedColumnName = "userPk"
    )
    private User user;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "addressFk",
            referencedColumnName = "addressId"
    )
    private Address address;

    private String emailAddress;

    private String shipPhoneNumber;

    private String orderId;
}
