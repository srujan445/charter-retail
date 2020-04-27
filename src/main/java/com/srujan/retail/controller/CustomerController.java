package com.srujan.retail.controller;

import com.srujan.retail.model.Customer;
import com.srujan.retail.service.CustomerService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/{id}")
  public Customer findById(@PathVariable long id) {
    return customerService.findById(id);
  }

  @GetMapping("/")
  public List<Customer> findCustomers() {
    return customerService.findCustomers();
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Customer updateCustomer(@PathVariable("id") final long id, @RequestBody final Customer customer) {
    return customerService.saveCustomer(customer);
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public Customer createCustomer(@NotNull @Valid @RequestBody final Customer customer) {
    return customerService.saveCustomer(customer);

  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public long deleteCustomer(@PathVariable final long id) {
    customerService.deleteCustomer(id);
    return id;
  }


}
