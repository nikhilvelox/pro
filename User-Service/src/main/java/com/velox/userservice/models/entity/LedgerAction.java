package com.velox.userservice.models.entity;

import com.velox.userservice.models.eums.Entry_Type;

public class LedgerAction extends BaseModel{
    private String trnsactionId;
    private String accountId;
    private Entry_Type entryType;
    private double amount;
}
