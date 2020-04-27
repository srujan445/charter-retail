package com.srujan.retail.model.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "ORDERS")
@EntityListeners(AuditEntityListener.class)
public class OrderEntity extends AuditEntity {

  @Column(name = "ORDER_ID", unique = true, nullable = false)
  @Id
  private long orderId;

  @JoinColumn(name = "CUSTOMER_ID")
  private long customerId;

  @Column(name = "PURCHASE_DATE")
  private LocalDate date;

  @Column(name = "AMOUNT")
  private BigDecimal amount;

}
