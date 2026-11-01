package com.velox.userservice.models.entity;

import com.velox.userservice.models.eums.Account_Type;
import com.velox.userservice.models.eums.Currency;
import com.velox.userservice.models.eums.Status;

public class Account  extends BaseModel{
    //userId
    private String accountNumber;
    private Account_Type accountType;
    private double balance;
    private Currency currency;
    private Status status;




}
