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
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineItemPk;

    private Integer quantity;

    private Integer status;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "product_fk",
            referencedColumnName = "productPk"
    )
    private Product product;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "order_summary_fk",
            referencedColumnName = "orderSummaryPk"
    )
    private OrderSummary orderSummaryFk;
}
