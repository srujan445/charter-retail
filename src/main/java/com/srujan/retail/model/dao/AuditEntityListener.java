package com.srujan.retail.model.dao;

import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AuditEntityListener {

  private static final String APP_NAME = "RETAIL";
  private static final String EMPLOYEE_ID = "EX123";

  @PrePersist
  void onPrePersist(Object entity) {
    if (AuditEntity.class.equals(entity.getClass().getSuperclass())) {
      AuditEntity model = (AuditEntity) entity;
      model.setCreateTimestamp(LocalDateTime.now());
      model.setCreatedBy(EMPLOYEE_ID);
      model.setCreateModule(APP_NAME);
    }
  }

  @PreUpdate
  void onPreUpdate(Object entity) {
    if (AuditEntity.class.equals(entity.getClass().getSuperclass())) {
      AuditEntity model = (AuditEntity) entity;
      model.setCreateTimestamp(((AuditEntity) entity).getCreateTimestamp() != null
          ? ((AuditEntity) entity).getCreateTimestamp() : LocalDateTime.now());
      model.setCreatedBy(StringUtils.isNotBlank(((AuditEntity) entity).getCreatedBy())
          ? ((AuditEntity) entity).getCreatedBy() : EMPLOYEE_ID);
      model.setCreateModule(StringUtils.isNotBlank(((AuditEntity) entity).getCreateModule())
          ? ((AuditEntity) entity).getCreateModule() : APP_NAME);
      model.setUpdateTimestamp(LocalDateTime.now());
      model.setUpdatedBy(EMPLOYEE_ID);
      model.setUpdateModule(APP_NAME);
    }
  }

}
