package com.velox.userservice.models.entity;

import com.velox.userservice.models.eums.Account_Type;
import com.velox.userservice.models.eums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity @Table(name="account_open_request") @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AccountOpenRequest extends BaseModel{
   // userId
    private Account_Type accountType;
    private Status status;
    private String revivedBy;
    private Instant revivedAt;
    private String rejection_reason;

}
