package com.srujan.retail.repository;

import com.srujan.retail.model.dao.OrderEntity;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
  public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

  @Query(value = "SELECT max(orderId) FROM OrderEntity")
  public long max();
  }




