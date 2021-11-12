package com.viswateja.farmstead.Repository;

import com.viswateja.farmstead.entity.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
}
