package com.srujan.retail.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.srujan.retail.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RewardsServiceTest {

  @InjectMocks
  private RewardsService rewardsService;
  @Mock
  private CustomerService mockCustomerService;
  @Mock
  private OrderService mockOrderService;

  @Test
  void findByOrderId_WhenOrderValueIs60_Than10PointsAreReturned() {
    when(mockOrderService.findById(15L)).thenReturn(getOrder());
    assertEquals(10, rewardsService.findByOrderId(15L));
  }

  @Test
  void findByOrderId_WhenOrderValueIs120_Than90PointsAreReturned() {
    Order order = getOrder();
    order.setAmount(new BigDecimal(120));
    when(mockOrderService.findById(15L)).thenReturn(order);
    assertEquals(90, rewardsService.findByOrderId(15L));
  }

  @Test
  void findByCustomer_whenMultipleOrdersArePresent_ThanPointsCalculatedCorrectly() {
    List<Order> orders = new ArrayList<>();
    orders.add(getOrder());
    Order order = getOrder();
    order.setAmount(new BigDecimal(120));
    orders.add(order);
    when(mockCustomerService.findOrders(123L, null)).thenReturn(orders);
    assertEquals(100, rewardsService.findByCustomer(123L, null));
  }

  private Order getOrder() {
    Order order = new Order();
    order.setAmount(new BigDecimal(60));
    order.setCustomerId(256L);
    LocalDate localDate = LocalDate.of(2018, Month.MAY, 20);
    order.setDate(localDate);
    order.setOrderId(15L);
    return order;
  }
}
