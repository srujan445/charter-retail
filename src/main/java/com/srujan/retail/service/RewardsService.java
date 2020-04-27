package com.srujan.retail.service;

import com.srujan.retail.model.Order;
import java.time.Month;
import java.util.List;
import java.util.function.ToLongFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardsService {

  private CustomerService customerService;
  private OrderService orderService;

  @Autowired
  public RewardsService(CustomerService customerService, OrderService orderService) {
    this.customerService = customerService;
    this.orderService = orderService;
  }

  public long findByOrderId(long orderId) {
    return getRewardsByOrder(orderService.findById(orderId));
  }

  public long findByCustomer(long customerId, Month month) {
    long points = 0;
    ToLongFunction<Order> func = this::getRewardsByOrder;
    List<Order> orders = customerService.findOrders(customerId, month);
    for (Order order : orders) {
      points = points + func.applyAsLong(order);
    }
    return points;
  }

  private long getRewardsByOrder(Order order) {
    if (order.getAmount().doubleValue() > 50 && order.getAmount().doubleValue() <= 100) {
      return Math.round(Math.ceil(order.getAmount().doubleValue()) - 50);
    }
    long points = Math.round(Math.ceil(order.getAmount().doubleValue()) - 100) * 2;
    points = points + 50;
    return points;
  }


}
