package com.viswateja.farmstead.Repository;

import com.viswateja.farmstead.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query(value = "update product set status = :status where sku = :sku", nativeQuery = true)
    public void updateStatusBySku(@Param("sku") String sku, @Param("status") Integer status);
}
