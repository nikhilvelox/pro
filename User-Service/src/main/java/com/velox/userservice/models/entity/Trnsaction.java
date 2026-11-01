package com.velox.userservice.models.entity;

import com.velox.userservice.models.eums.TransactionStatus;
import com.velox.userservice.models.eums.TransactionType;

public class Trnsaction extends BaseModel{

    private String refrenceId;
    private String idempotencyKey;
    private TransactionType transactionType;
    private String sourceAccountId;
    private String destincation;
    private double ammount;
    private TransactionStatus status;
    private String description;

}
