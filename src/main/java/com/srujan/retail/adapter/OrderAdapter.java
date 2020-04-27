package com.srujan.retail.adapter;

import com.srujan.retail.model.Order;
import com.srujan.retail.model.dao.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderAdapter {

  public Order convert(OrderEntity orderEntity) {
    if (orderEntity == null) {
      return null;
    }
    Order order = new Order();
    order.setAmount(orderEntity.getAmount());
    order.setCustomerId(orderEntity.getCustomerId());
    order.setDate(orderEntity.getDate());
    order.setOrderId(orderEntity.getOrderId());
    return order;
  }

  public OrderEntity convert(Order order) {
    if (order == null) {
      return null;
    }
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setAmount(order.getAmount());
    orderEntity.setCustomerId(order.getCustomerId());
    orderEntity.setDate(order.getDate());
    orderEntity.setOrderId(order.getOrderId());
    return orderEntity;
  }

}
