package com.srujan.retail.service;

import com.srujan.retail.adapter.CustomerAdapter;
import com.srujan.retail.exception.CustomerNotFoundException;
import com.srujan.retail.model.Customer;
import com.srujan.retail.model.Order;
import com.srujan.retail.model.dao.OrderEntity;
import com.srujan.retail.repository.CustomerRepository;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

  private CustomerRepository repository;

  private CustomerAdapter adapter;

  @Autowired
  public CustomerService(CustomerRepository repository, CustomerAdapter adapter) {
    this.repository = repository;
    this.adapter = adapter;
  }

  public Customer findById(long id) {
    return adapter.convert(repository.findById(id)
        .orElseThrow(CustomerNotFoundException::new));
  }

  public List<Customer> findCustomers() {
    return adapter.convert(repository.findAll());
  }

  @Transactional
  public List<Order> findOrders(long customerId, Month month) {
    try (Stream<OrderEntity> orderStream = repository.findAllOrdersByCustomerId(customerId)
        .filter(order -> order.getAmount().doubleValue() > 50)) {
      if (month != null) {
        return adapter.convert(orderStream.filter(order -> order.getDate().getMonth().equals(month))
            .collect(Collectors.toList()));
      }
      return adapter.convert(orderStream.collect(Collectors.toList()));
    }
  }

  public Customer saveCustomer(Customer customer) {
    if(customer.getCustomerId() == 0) {
        customer.setCustomerId(repository.max()+1);
    }
    return adapter.convert(repository.save(adapter.convert(customer)));
  }

  public void deleteCustomer(long id) {
    repository.deleteById(id);
  }


}
