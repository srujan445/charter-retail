package com.srujan.retail.model.dao;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "CUSTOMERS")
@EntityListeners(AuditEntityListener.class)
public class CustomerEntity extends AuditEntity {

  @Column(name = "CUSTOMER_ID", unique = true, nullable = false)
  @Id
  private long customerId;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "PHONE_NUMBER")
  private String phoneNumber;

  @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL)
  @ToString.Include
  private List<OrderEntity> orderEntities;

}
