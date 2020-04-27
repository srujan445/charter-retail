package com.srujan.retail.repository;

import com.srujan.retail.model.dao.CustomerEntity;
import com.srujan.retail.model.dao.OrderEntity;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

  @Query("select c.orderEntities from CustomerEntity c where c.customerId = :customerId")
  Stream<OrderEntity> findAllOrdersByCustomerId(long customerId);

  @Query(value = "SELECT max(customerId) FROM CustomerEntity")
  public long max();
}
