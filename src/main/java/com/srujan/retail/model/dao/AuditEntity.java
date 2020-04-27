package com.srujan.retail.model.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(exclude = {"updateTimestamp", "updateModule", "updatedBy"})
@MappedSuperclass
public abstract class AuditEntity implements Serializable {

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "create_module")
  private String createModule;

  @Column(name = "create_timestamp")
  private LocalDateTime createTimestamp;

  @Column(name = "updated_by")
  private String updatedBy;

  @Column(name = "update_module")
  private String updateModule;

  @Column(name = "update_timestamp")
  private LocalDateTime updateTimestamp;

}
