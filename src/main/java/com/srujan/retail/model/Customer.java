package com.srujan.retail.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Customer {

  private long customerId;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private List<Order> orders;

}
