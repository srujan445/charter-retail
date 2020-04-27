package com.srujan.retail.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.srujan.retail.model.Order;
import com.srujan.retail.model.dao.OrderEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderAdapterTest {

  private OrderAdapter orderAdapter;

  @BeforeEach
  void setUp() {
    orderAdapter = new OrderAdapter();
  }

  @Test
  void convert_whenOrderEntityIsNull_ThanOrderIsNull() {
    OrderEntity orderEntity = null;
    assertNull(orderAdapter.convert(orderEntity));
  }

  @Test
  void convert_whenOrderEntityIsEmpty_ThanOrderIsEmpty() {
    OrderEntity orderEntity = new OrderEntity();
    Order order = orderAdapter.convert(orderEntity);
    assertNotNull(order);
    assertEquals(0, order.getCustomerId());
  }

  @Test
  void convert_whenOrderEntityIsPresent_ThanOrderIsPresent() {
    Order order = orderAdapter.convert(getOrderEntity());
    assertNotNull(order);
    assertEquals(123, order.getCustomerId());
    assertEquals(250, order.getAmount().doubleValue());
    assertEquals(LocalDate.of(2019, Month.APRIL, 25), order.getDate());
    assertEquals(12, order.getOrderId());
  }

  @Test
  void convert_whenOrderIsNull_ThanOrderEntityIsNull() {
    Order order = null;
    assertNull(orderAdapter.convert(order));
  }

  @Test
  void convert_whenOrderIsEmpty_ThanOrderEntityIsEmpty() {
    Order order = new Order();
    OrderEntity orderEntity = orderAdapter.convert(order);
    assertNotNull(orderEntity);
    assertEquals(0, orderEntity.getCustomerId());
  }

  @Test
  void convert_whenOrderIsPresent_ThanOrderEntityIsPresent() {
    OrderEntity orderEntity = orderAdapter.convert(getOrder());
    assertNotNull(orderEntity);
    assertEquals(256, orderEntity.getCustomerId());
    assertEquals(500, orderEntity.getAmount().doubleValue());
    assertEquals(LocalDate.of(2018, Month.MAY, 20), orderEntity.getDate());
    assertEquals(15, orderEntity.getOrderId());
  }


  private OrderEntity getOrderEntity() {
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setAmount(new BigDecimal(250));
    orderEntity.setCustomerId(123L);
    LocalDate localDate = LocalDate.of(2019, Month.APRIL, 25);
    orderEntity.setDate(localDate);
    orderEntity.setOrderId(12L);
    return orderEntity;
  }

  private Order getOrder() {
    Order order = new Order();
    order.setAmount(new BigDecimal(500));
    order.setCustomerId(256L);
    LocalDate localDate = LocalDate.of(2018, Month.MAY, 20);
    order.setDate(localDate);
    order.setOrderId(15L);
    return order;
  }
}
