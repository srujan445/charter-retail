package com.srujan.retail.controller;

import com.srujan.retail.service.RewardsService;
import java.time.Month;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

  final RewardsService rewardsService;

  public RewardsController(RewardsService rewardsService) {
    this.rewardsService = rewardsService;
  }

  @GetMapping("order/{orderId}")
  public long findByOrderId(@PathVariable long orderId) {
    return rewardsService.findByOrderId(orderId);
  }


  @GetMapping("customer/{customerId}")
  public long findByCustomerId(@PathVariable long customerId) {
    return rewardsService.findByCustomer(customerId, null);
  }

  @GetMapping("customer/{customerId}/{month}")
  public long findByCustomerIdAndMonth(@PathVariable long customerId,@PathVariable Month month) {
    return rewardsService.findByCustomer(customerId, month);
  }

 }
