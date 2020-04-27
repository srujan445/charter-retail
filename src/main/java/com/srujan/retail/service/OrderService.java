package com.srujan.retail.service;

import com.srujan.retail.adapter.OrderAdapter;
import com.srujan.retail.exception.OrderNotFoundException;
import com.srujan.retail.model.Order;
import com.srujan.retail.model.dao.OrderEntity;
import com.srujan.retail.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepository repository;
  private final OrderAdapter orderAdapter;

  @Autowired
  public OrderService(OrderRepository repository, OrderAdapter orderAdapter) {
    this.repository = repository;
    this.orderAdapter = orderAdapter;
  }

  public Order findById(long id) {
    return orderAdapter.convert(repository.findById(id).orElseThrow(OrderNotFoundException::new));
  }

  public Order saveOrder(Order order) {
    if(order.getOrderId() == 0) {
      order.setOrderId(repository.max()+1);
    }
    return orderAdapter.convert(repository.save(orderAdapter.convert(order)));
  }

  public void deleteOrder(long id) {
    repository.deleteById(id);
  }

}
