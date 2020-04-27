package com.srujan.retail.adapter;

import com.srujan.retail.model.Customer;
import com.srujan.retail.model.Order;
import com.srujan.retail.model.dao.CustomerEntity;
import com.srujan.retail.model.dao.OrderEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class CustomerAdapter {

  private OrderAdapter orderAdapter;

  @Autowired
  public CustomerAdapter(OrderAdapter orderAdapter) {
    this.orderAdapter = orderAdapter;
  }

  public List<Customer> convert(Collection<CustomerEntity> customerEntities) {
    List<Customer> customers = new ArrayList<>();
    if (!CollectionUtils.isEmpty(customerEntities)) {
      for (CustomerEntity customerEntity : customerEntities) {
        customers.add(convert(customerEntity));
      }
    }
    return customers;
  }

  public Customer convert(CustomerEntity customerEntity) {
    if (customerEntity == null) {
      return null;
    }
    Customer customer = new Customer();
    customer.setCustomerId(customerEntity.getCustomerId());
    customer.setFirstName(customerEntity.getFirstName());
    customer.setLastName(customerEntity.getLastName());
    customer.setPhoneNumber(customerEntity.getPhoneNumber());
    List<Order> orders = new ArrayList<>();
    if (!CollectionUtils.isEmpty(customerEntity.getOrderEntities())) {
      for (OrderEntity orderEntity : customerEntity.getOrderEntities()) {
        if (orderEntity != null) {
          orders.add(orderAdapter.convert(orderEntity));
        }
      }
    }
    customer.setOrders(orders);
    return customer;
  }

  public CustomerEntity convert(Customer customer) {
    if (customer == null) {
      return null;
    }
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setCustomerId(customer.getCustomerId());
    customerEntity.setFirstName(customer.getFirstName());
    customerEntity.setLastName(customer.getLastName());
    customerEntity.setPhoneNumber(customer.getPhoneNumber());
    List<OrderEntity> orderEntities = new ArrayList<>();
    if (!CollectionUtils.isEmpty(customer.getOrders())) {
      for (Order order : customer.getOrders()) {
        if (order != null) {
          orderEntities.add(orderAdapter.convert(order));
        }
      }
    }
    customerEntity.setOrderEntities(orderEntities);
    return customerEntity;
  }

  public List<Order> convert(List<OrderEntity> orderEntities) {
    List<Order> orders = new ArrayList<>();
    if (!CollectionUtils.isEmpty(orderEntities)) {
      for (OrderEntity orderEntity : orderEntities) {
        if (orderEntity != null) {
          orders.add(orderAdapter.convert(orderEntity));
        }
      }
    }
    return orders;
  }
}
