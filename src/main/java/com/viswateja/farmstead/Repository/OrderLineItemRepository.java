package com.viswateja.farmstead.Repository;

import com.viswateja.farmstead.entity.OrderLineItem;
import com.viswateja.farmstead.entity.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
    @Query("select oli from OrderLineItem oli where oli.orderSummaryFk.orderSummaryPk = ?1")
    public List<OrderLineItem> getOrdersByOrderId(Long orderSummaryPk);

    @Modifying
    @Transactional
    @Query(value = "update order_line_item set status = 2 where status = 0 ;", nativeQuery = true)
    public void shipOrders();
}
