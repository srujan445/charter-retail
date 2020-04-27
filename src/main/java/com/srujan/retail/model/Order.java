package com.srujan.retail.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Order {

  private long orderId;
  private long customerId;
  private LocalDate date;
  private BigDecimal amount;

}
