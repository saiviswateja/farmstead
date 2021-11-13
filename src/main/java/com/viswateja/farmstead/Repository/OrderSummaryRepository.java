package com.viswateja.farmstead.Repository;

import com.viswateja.farmstead.DTO.Requests.OrderRequest;
import com.viswateja.farmstead.entity.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSummaryRepository extends JpaRepository<OrderSummary, Long> {
    @Query("select s from OrderSummary s where s.orderId = ?1")
    public OrderSummary getOrderSummaryByOrderId(String orderId);
}
