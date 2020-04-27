package com.srujan.retail.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.srujan.retail.model.Customer;
import com.srujan.retail.model.Order;
import com.srujan.retail.model.dao.CustomerEntity;
import com.srujan.retail.model.dao.OrderEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerAdapterTest {

  @InjectMocks
  private CustomerAdapter customerAdapter;

  @Mock
  private OrderAdapter mockOrderAdapter;

  @Test
  void convert_WhenCustomerEntityIsNull_ThanCustomerIsNull() {
    CustomerEntity customerEntity = null;
    assertNull(customerAdapter.convert(customerEntity));
  }

  @Test
  void convert_WhenCustomerEntityIsEmpty_ThanCustomerIsEmpty() {
    CustomerEntity customerEntity = new CustomerEntity();
    Customer customer = customerAdapter.convert(customerEntity);
    assertNotNull(customer);
    assertEquals(0, customer.getCustomerId());
  }

  @Test
  void convert_WhenCustomerEntityIsSent_ThanCustomerIsPresent() {
    Customer customer = customerAdapter.convert(getCustomerEntity());
    assertNotNull(customer);
    assertEquals(12, customer.getCustomerId());
    assertEquals("Srujan", customer.getFirstName());
    assertEquals("Eppa", customer.getLastName());
    assertEquals("2512512511", customer.getPhoneNumber());
    assertEquals(1, customer.getOrders().size());
    assertNull(customer.getOrders().get(0));
  }

  @Test
  void convert_WhenCustomerIsNull_ThanCustomerEntityIsNull() {
    Customer customer = null;
    assertNull(customerAdapter.convert(customer));
  }

  @Test
  void convert_WhenCustomerIsEmpty_ThanCustomerEntityIsEmpty() {
    Customer customer = new Customer();
    CustomerEntity customerEntity = customerAdapter.convert(customer);
    assertNotNull(customerEntity);
    assertEquals(0, customerEntity.getCustomerId());
  }

  @Test
  void convert_WhenCustomerIsSent_ThanCustomerEntityIsPresent() {
    CustomerEntity customerEntity = customerAdapter.convert(getCustomer());
    assertNotNull(customerEntity);
    assertEquals(12, customerEntity.getCustomerId());
    assertEquals("Srujan", customerEntity.getFirstName());
    assertEquals("Eppa", customerEntity.getLastName());
    assertEquals("2512512511", customerEntity.getPhoneNumber());
    assertEquals(1, customerEntity.getOrderEntities().size());
    assertNull(customerEntity.getOrderEntities().get(0));
  }

  @Test
  void convert_whenOrderEntitiesIsNull_ThenOrdersIsEmpty() {
    List<OrderEntity> orderEntities = null;
    List<Order> orders = customerAdapter.convert(orderEntities);
    assertNotNull(orders);
    assertTrue(orders.isEmpty());
  }

  @Test
  void convert_whenOrderEntitiesIsEmpty_ThenOrdersIsEmpty() {
    List<OrderEntity> orderEntities = new ArrayList<>();
    List<Order> orders = customerAdapter.convert(orderEntities);
    assertNotNull(orders);
    assertTrue(orders.isEmpty());
  }

  @Test
  void convert_whenOrderEntitiesArePresent_ThenOrdersAreReturned() {
    List<OrderEntity> orderEntities = new ArrayList<>();
    OrderEntity orderEntity = new OrderEntity();
    orderEntities.add(orderEntity);
    List<Order> orders = customerAdapter.convert(orderEntities);
    assertNotNull(orders);
    assertEquals(1, orders.size());
  }

  @Test
  void convert_whenCustomerEntitiesIsNull_ThenCustomersIsEmpty() {
    List<CustomerEntity> customerEntities = null;
    List<Customer> customers = customerAdapter.convert(customerEntities);
    assertNotNull(customers);
    assertTrue(customers.isEmpty());
  }

  @Test
  void convert_whenCustomerEntitiesIsEmpty_ThenCustomersIsEmpty() {
    List<CustomerEntity> customerEntities = new ArrayList<>();
    List<Customer> customers = customerAdapter.convert(customerEntities);
    assertNotNull(customers);
    assertTrue(customers.isEmpty());
  }

  @Test
  void convert_whenCustomerEntitiesArePresent_ThenCustomersAreReturned() {
    List<CustomerEntity> customerEntities = new ArrayList<>();
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntities.add(customerEntity);
    List<Customer> customers = customerAdapter.convert(customerEntities);
    assertNotNull(customers);
    assertEquals(1, customers.size());
  }

  private CustomerEntity getCustomerEntity() {
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setCustomerId(12);
    customerEntity.setFirstName("Srujan");
    customerEntity.setLastName("Eppa");
    customerEntity.setPhoneNumber("2512512511");
    List<OrderEntity> orderEntities = new ArrayList<>();
    OrderEntity orderEntity = new OrderEntity();
    orderEntities.add(orderEntity);
    customerEntity.setOrderEntities(orderEntities);
    return customerEntity;
  }

  private Customer getCustomer() {
    Customer customer = new Customer();
    customer.setCustomerId(12);
    customer.setFirstName("Srujan");
    customer.setLastName("Eppa");
    customer.setPhoneNumber("2512512511");
    List<Order> orders = new ArrayList<>();
    Order order = new Order();
    orders.add(order);
    customer.setOrders(orders);
    return customer;
  }
}
